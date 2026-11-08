package bdcc.devops.billingservice.feign;

import bdcc.devops.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);
}
