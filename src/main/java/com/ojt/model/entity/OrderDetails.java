package com.ojt.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn( name="history_id", referencedColumnName = "id")
    private Orders history;
    @ManyToOne
    @JoinColumn( name="product_id", referencedColumnName = "productId")
    private Product product;
    private Integer quantity;
    private Double price;



}
