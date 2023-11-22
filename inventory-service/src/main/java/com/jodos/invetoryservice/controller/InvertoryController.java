package com.jodos.invetoryservice.controller;

import com.jodos.invetoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InvertoryController {
    private final InventoryService service;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean inInStock(@PathVariable("sku-code") String skuCode) {

        return service.isInStock(skuCode);
    }

}
