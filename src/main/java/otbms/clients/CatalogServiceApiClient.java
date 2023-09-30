package otbms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "catalogSvcClient", path = "/catalog-svc/v1/shows", url = "http://localhost:9090")
public interface CatalogServiceApiClient {

    @GetMapping("/{id}/free-seats")
    Integer getFreeSeatsCount(@PathVariable("id") Integer showId);
}
