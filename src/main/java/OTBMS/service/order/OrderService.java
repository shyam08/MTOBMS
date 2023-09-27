package OTBMS.service.order;

import OTBMS.dto.order.OrderCreateRequest;
import OTBMS.dto.order.OrderCreateResponse;
import OTBMS.dto.order.OrderProcessRequest;
import OTBMS.dto.order.OrderProcessResponse;

public interface OrderService {
    OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception;

    OrderProcessResponse processOrder(OrderProcessRequest request) throws Exception;

}
