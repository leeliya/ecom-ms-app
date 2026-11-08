package bdcc.devops.inventoryservice;

import bdcc.devops.inventoryservice.entities.Product;
import bdcc.devops.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Computer").price(3200).quantity(11).build());
            productRepository.save(Product.builder().name("Printer").price(1299).quantity(10).build());
            productRepository.save(Product.builder().name("Smartphone").price(5400).quantity(8).build());
            productRepository.findAll().forEach(p -> {
                System.out.println("==================");
                System.out.println(p.getId());
                System.out.println(p.getName());
                System.out.println(p.getPrice());
                System.out.println(p.getQuantity());
            });
        };
    }
}
