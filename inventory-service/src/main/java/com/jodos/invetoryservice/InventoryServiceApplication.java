package com.jodos.invetoryservice;

import com.jodos.invetoryservice.model.Inventory;
import com.jodos.invetoryservice.repository.InventoryRepository;
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
    public CommandLineRunner loadData(InventoryRepository repository) {

        return args -> {
            Inventory inventory = Inventory.builder()
                    .skuCode("iphone_13")
                    .quantity(100)
                    .build();
            Inventory inventory1 = Inventory.builder()
                    .skuCode("iphone_13_red")
                    .quantity(0)
                    .build();
            repository.save(inventory);
            repository.save(inventory1);
        };
    }
}
