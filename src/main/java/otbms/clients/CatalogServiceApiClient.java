package otbms.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import otbms.exception.InvalidPaymentGatewayException;

import javax.naming.ServiceUnavailableException;

@FeignClient(value = "catalogSvcClient", path = "/catalog-svc/v1/shows", url = "http://localhost:9090")
@CircuitBreaker(name="payment-Service", fallbackMethod = "responseFallBack")

public interface CatalogServiceApiClient {

    @GetMapping("/{id}/free-seats")
    Integer getFreeSeatsCount(@PathVariable("id") Integer showId);

    InvalidPaymentGatewayException responseFallBack(ServiceUnavailableException suex);
}
