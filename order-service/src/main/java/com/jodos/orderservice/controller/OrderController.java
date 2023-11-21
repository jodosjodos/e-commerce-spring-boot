package com.jodos.orderservice.controller;

import com.jodos.orderservice.dto.OrderRequest;
import com.jodos.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
   private final OrderService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  String placeOrder(@RequestBody OrderRequest orderRequest){
        service.placeOrder(orderRequest);
        return  " Order placed  successfully";
    }
}
