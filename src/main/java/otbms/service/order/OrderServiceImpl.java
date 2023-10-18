package otbms.service.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import otbms.clients.PaymentServiceApiClient;
import otbms.controller.order.OrderAction;
import otbms.dao.order.Order;
import otbms.dao.order.OrderDetail;
import otbms.dao.order.OrderDetailRepository;
import otbms.dao.order.OrderRepository;
import otbms.dto.cart.CartUpsertResponse;
import otbms.dto.order.OrderCreateRequest;
import otbms.dto.order.OrderCreateResponse;
import otbms.dto.order.OrderProcessRequest;
import otbms.dto.order.OrderProcessResponse;
import otbms.dto.payment.PaymentAction;
import otbms.dto.payment.PaymentInitiateRequest;
import otbms.dto.payment.PaymentInitiateResponse;
import otbms.exception.InvalidActionException;

import otbms.service.cart.CartService;
import otbms.utils.Common;
import otbms.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentServiceApiClient paymentServiceApiClient;

    @Override
    public OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception {
        CartUpsertResponse cartDetail = cartService.getCart(request.getCartId());
        OrderDetail orderDetail = orderDetailRepository.save(new OrderDetail());
        Order order = orderRepository.save(new Order(null, orderDetail, OrderAction.initiatePayment.ordinal(),
                Common.getTransactionId(), Status.pending.ordinal(), null, null, null));
        return new OrderCreateResponse(order.getId(), order.getOrderPaymentId(), OrderAction.initiatePayment);
    }

    @Override
    @CircuitBreaker(name="order-Service", fallbackMethod = "responseFallBack")
    public OrderProcessResponse processOrder(OrderProcessRequest request) throws Exception {

                try {
                    PaymentInitiateResponse response = paymentServiceApiClient.initiate(new PaymentInitiateRequest());
                    Order order = orderRepository.findById(request.getOrderId()).get();
                    order.setStatus(Status.processing.ordinal());
                    OrderProcessResponse orderProcessResponse = new OrderProcessResponse();
                    orderProcessResponse = updateOrderOnPayment(request);
                    //TODO: publish seats booking status
                    return  orderProcessResponse;
                }catch(Exception e) {
                    throw new InvalidActionException(String.format("invalid action %s", request.getAction()));
                }
        }
   void responseFallBack(){
       // take action
    }

    protected OrderProcessResponse updateOrderOnPayment(OrderProcessRequest request) {
        Order order = orderRepository.findByOrderPaymentId(request.getPaymentTransactionId()).get();
        order.setStatus(request.getPaymentStatus().ordinal());
        order.setNextAction(request.getAction().ordinal());
        order.setReason(request.getReason());
        orderRepository.save(order);
        return new OrderProcessResponse(order.getId(), request.getPaymentTransactionId(), request.getPaymentGtwId(), PaymentAction.none,
                null);
    }
}
