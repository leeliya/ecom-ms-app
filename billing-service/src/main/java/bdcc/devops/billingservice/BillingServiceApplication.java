package bdcc.devops.billingservice;

import bdcc.devops.billingservice.entities.Bill;
import bdcc.devops.billingservice.entities.ProductItem;
import bdcc.devops.billingservice.repository.BillRepository;
import bdcc.devops.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository) {
        return args -> {
            // les ids 1, 2 (clients) et 1, 2, 3 (produits) existent grace aux donnees de test des autres services
            for (long customerId = 1; customerId <= 2; customerId++) {
                Bill bill = billRepository.save(Bill.builder()
                        .billingDate(new Date())
                        .customerId(customerId)
                        .build());
                for (long productId = 1; productId <= 3; productId++) {
                    productItemRepository.save(ProductItem.builder()
                            .bill(bill)
                            .productId(productId)
                            .quantity((int) (1 + Math.random() * 10))
                            .unitPrice(1000 + Math.random() * 4000)
                            .build());
                }
            }
        };
    }
}
