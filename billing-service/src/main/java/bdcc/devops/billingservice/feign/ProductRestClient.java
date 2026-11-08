package bdcc.devops.billingservice.feign;

import bdcc.devops.billingservice.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getDefaultProduct")
    Product findProductById(@PathVariable("id") Long id);

    default Product getDefaultProduct(Long id, Exception e) {
        Product product = new Product();
        product.setId(id);
        product.setName("Not Available");
        return product;
    }
}
