package OTBMS.clients;

import jakarta.validation.Valid;
import OTBMS.dto.order.OrderProcessRequest;
import OTBMS.dto.order.OrderProcessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "orderSvcClient", path = "/order-svc/v1/orders", url = "http://localhost:9090")
public interface OrderServiceApiClient {

    @PostMapping("/process")
    OrderProcessResponse orderProcess(@Valid @RequestBody OrderProcessRequest request);
}
