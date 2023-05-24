package com.microservice.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="t_order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="order_number")
    private String orderNumber;
    @Column(name="sku_code")
    private String skuCode;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="quantity")
    private Integer quantity;
}
