package OTBMS.service.order;

import OTBMS.clients.PaymentServiceApiClient;
import OTBMS.controller.order.OrderAction;
import OTBMS.dao.order.Order;
import OTBMS.dao.order.OrderDetail;
import OTBMS.dao.order.OrderDetailRepository;
import OTBMS.dao.order.OrderRepository;
import OTBMS.dto.cart.CartUpsertResponse;
import OTBMS.dto.order.OrderCreateRequest;
import OTBMS.dto.order.OrderCreateResponse;
import OTBMS.dto.order.OrderProcessRequest;
import OTBMS.dto.order.OrderProcessResponse;
import OTBMS.dto.payment.PaymentAction;
import OTBMS.dto.payment.PaymentInitiateRequest;
import OTBMS.dto.payment.PaymentInitiateResponse;
import OTBMS.exception.InvalidActionException;

import OTBMS.service.cart.CartService;
import OTBMS.utils.Common;
import OTBMS.utils.Status;
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
