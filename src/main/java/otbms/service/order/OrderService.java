package otbms.service.order;

import otbms.dto.order.OrderCreateRequest;
import otbms.dto.order.OrderCreateResponse;
import otbms.dto.order.OrderProcessRequest;
import otbms.dto.order.OrderProcessResponse;

public interface OrderService {
    OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception;

    OrderProcessResponse processOrder(OrderProcessRequest request) throws Exception;

}
