package otbms.service.order;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import otbms.dto.order.OrderCreateRequest;
import otbms.dto.order.OrderCreateResponse;
import otbms.dto.order.OrderProcessRequest;
import otbms.dto.order.OrderProcessResponse;

public interface OrderService {
    OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    OrderProcessResponse processOrder(OrderProcessRequest request) throws Exception;

}
