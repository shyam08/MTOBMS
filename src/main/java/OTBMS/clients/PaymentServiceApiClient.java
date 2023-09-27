package OTBMS.clients;

import jakarta.validation.Valid;
import OTBMS.dto.payment.PaymentInitiateRequest;
import OTBMS.dto.payment.PaymentInitiateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "paymentSvcClient", path = "/payment-service/v1/payments", url = "http://localhost:9090")
public interface PaymentServiceApiClient {

    @PostMapping("/initiate")
    PaymentInitiateResponse initiate(@Valid @RequestBody PaymentInitiateRequest request);
}
