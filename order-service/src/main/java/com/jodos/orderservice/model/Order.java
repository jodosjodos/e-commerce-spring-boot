package com.jodos.orderservice.model;

import com.jodos.orderservice.dto.OrderLineItems;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
private  String orderNumber;
@OneToMany(cascade = CascadeType.ALL)
private List<OrderLineItems> orderLineItemsList;
}
