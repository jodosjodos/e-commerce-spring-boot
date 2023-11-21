package com.jodos.product.service.repository;

import com.jodos.product.service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository  extends MongoRepository<Product,String> {
}
