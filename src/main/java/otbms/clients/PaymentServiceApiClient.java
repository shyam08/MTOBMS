package otbms.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import otbms.dto.payment.PaymentInitiateRequest;
import otbms.dto.payment.PaymentInitiateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import otbms.exception.InvalidPaymentGatewayException;

import javax.naming.ServiceUnavailableException;

@FeignClient(value = "paymentSvcClient", path = "/payment-service/v1/payments", url = "http://localhost:8080")
public interface PaymentServiceApiClient {

    @PostMapping("/initiate")
    PaymentInitiateResponse initiate(@Valid @RequestBody PaymentInitiateRequest request);

}
