package com.jodos.product.service.service;

import com.jodos.product.service.dto.ProductRequest;
import com.jodos.product.service.dto.ProductResponse;
import com.jodos.product.service.model.Product;
import com.jodos.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();


        repository.save(product);
        log.info(" product with {} id  is save" ,product.getId());
    }


    public List<ProductResponse> getAllProducts() {
    List<Product>  products= repository.findAll();
return    products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
