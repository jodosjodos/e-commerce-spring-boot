package com.jodos.invetoryservice.services;

import com.jodos.invetoryservice.dto.InventoryRequest;
import com.jodos.invetoryservice.dto.InventoryResponse;
import com.jodos.invetoryservice.model.Inventory;
import com.jodos.invetoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository repository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("wait started");
        Thread.sleep(1000);
        log.info("wait ended");
        return repository.findBySkuCodeIn(skuCode).stream().map(inventory ->
                InventoryResponse
                        .builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
        ).toList();
    }

    public List<Inventory> addInventoryToStock(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory
                .builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        repository.save(inventory);
        return repository.findAll();
    }
}
