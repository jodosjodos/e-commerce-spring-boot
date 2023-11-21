package com.jodos.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jodos.product.service.dto.ProductRequest;
import com.jodos.product.service.dto.ProductResponse;
import com.jodos.product.service.model.Product;
import com.jodos.product.service.repository.ProductRepository;
import com.jodos.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
class ApplicationTests {
    @AfterAll
    static void tearDown() {
        mongoDBContainer.stop();
    }

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    ProductRepository repository;

    @Autowired
    private ProductService service;

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    void shouldCreateProduct() throws Exception {
        // Clear existing data in the database
        repository.deleteAll();

        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000); // Wait for 1 second (adjust the time as needed)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            Assertions.assertEquals(1, repository.findAll().size());

        });
        // Now check your assertion
        future.get();

    }


    private ProductRequest getProductRequest() {
        return ProductRequest.builder().name("i phone 13").description("i phone 13").price(BigDecimal.valueOf(1200)).build();
    }


    //    get all product test
    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(
                Product.builder().id("11").name("product1").description("description1").price(BigDecimal.valueOf(2200)).build(),
                Product.builder().id("22").name("Product2").description("Description2").price(BigDecimal.valueOf(200)).build()
        );


        when(repository.findAll()).thenReturn(mockProducts);
        List<ProductResponse> result = service.getAllProducts();
        Assertions.assertEquals(2, result.size());
        ProductResponse response1 = result.get(0);
        Assertions.assertEquals("11", response1.getId());

        ProductResponse response2 = result.get(1);
        Assertions.assertEquals("22", response2.getId());
    }
}
