package com.jodos.product.service.controller;

import com.jodos.product.service.dto.ProductRequest;
import com.jodos.product.service.dto.ProductResponse;
import com.jodos.product.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        service.createProduct(productRequest);
    }

//    retrieve all products
   @GetMapping
   @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
     return    service.getAllProducts();
   }

}
