package bdcc.devops.customerservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

// Classe mutable (getters/setters) : un record est immuable et ne peut pas etre re-lie lors d'un refresh
@ConfigurationProperties(prefix = "customer.params")
@Getter
@Setter
public class CustomerConfigParams {
    private int x;
    private int y;
}
