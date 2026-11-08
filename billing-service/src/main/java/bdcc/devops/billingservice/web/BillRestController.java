package bdcc.devops.billingservice.web;

import bdcc.devops.billingservice.entities.Bill;
import bdcc.devops.billingservice.feign.CustomerRestClient;
import bdcc.devops.billingservice.feign.ProductRestClient;
import bdcc.devops.billingservice.repository.BillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    private final BillRepository billRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository,
                              CustomerRestClient customerRestClient,
                              ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi ->
                pi.setProduct(productRestClient.findProductById(pi.getProductId())));
        return bill;
    }
}
