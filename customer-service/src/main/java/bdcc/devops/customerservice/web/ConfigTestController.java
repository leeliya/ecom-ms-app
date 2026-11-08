package bdcc.devops.customerservice.web;

import bdcc.devops.customerservice.config.CustomerConfigParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {
    @Value("${global.message:message par defaut}")
    private String message;
    private final CustomerConfigParams params;

    public ConfigTestController(CustomerConfigParams params) {
        this.params = params;
    }

    @GetMapping("/params")
    public Map<String, Object> params() {
        return Map.of("message", message, "x", params.getX(), "y", params.getY());
    }
}
