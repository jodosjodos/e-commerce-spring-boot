package com.jodos.orderservice.event;

// when order is placed  produce this to  kafka topic

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private  String orderNumber;
}
