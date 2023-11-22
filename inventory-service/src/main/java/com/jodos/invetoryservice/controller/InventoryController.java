package com.jodos.invetoryservice.controller;

import com.jodos.invetoryservice.dto.InventoryRequest;
import com.jodos.invetoryservice.dto.InventoryResponse;
import com.jodos.invetoryservice.model.Inventory;
import com.jodos.invetoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCodes) {

        return service.isInStock(skuCodes);
    }

    //     add product to your store
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Inventory> addInventoryToStock(@RequestBody InventoryRequest inventoryRequest) {
        return service.addInventoryToStock(inventoryRequest);
    }

}
