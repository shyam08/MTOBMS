package otbms.service.order;

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
    public OrderProcessResponse processOrder(OrderProcessRequest request) throws Exception {
        switch (request.getAction()) {
            case initiatePayment:
                PaymentInitiateResponse response = paymentServiceApiClient.initiate(new PaymentInitiateRequest());
                Order order = orderRepository.findById(request.getOrderId()).get();
                order.setStatus(Status.processing.ordinal());
                return new OrderProcessResponse(request.getOrderId(), response.getTransactionId(),
                        response.getPayementGtwId(), response.getNextAction(), response.getMetaData());
            case paymentConfirmation:
                return updateOrderOnPayment(request);
            default:
                throw new InvalidActionException(String.format("invalid action %s", request.getAction()));
        }
    }

    protected OrderProcessResponse updateOrderOnPayment(OrderProcessRequest request) {
        Order order = orderRepository.findByOrderPaymentId(request.getPaymentTransactionId()).get();
        order.setStatus(request.getPaymentStatus().ordinal());
        order.setNextAction(request.getAction().ordinal());
        order.setReason(request.getReason());
        orderRepository.save(order);
        //TODO: publish seats booking status

        return new OrderProcessResponse(order.getId(), request.getPaymentTransactionId(), request.getPaymentGtwId(), PaymentAction.none,
                null);
    }
}