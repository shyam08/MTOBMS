package otbms.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import otbms.dto.order.OrderProcessRequest;
import otbms.dto.order.OrderProcessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import otbms.exception.InvalidActionException;
import otbms.exception.InvalidPaymentGatewayException;

import javax.naming.ServiceUnavailableException;

@FeignClient(value = "orderSvcClient", path = "/order-svc/v1/orders", url = "http://localhost:9090")
@CircuitBreaker(name="payment-Service", fallbackMethod = "responseFallBack")

public interface OrderServiceApiClient {

    @PostMapping("/process")
    OrderProcessResponse orderProcess(@Valid @RequestBody OrderProcessRequest request);
    InvalidActionException responseFallBack(ServiceUnavailableException suex);
}
