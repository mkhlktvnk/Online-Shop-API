package edu.bsuir.sneakersshop.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer productSize;

    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @PrePersist
    private void calculateTotalPrice() {
        totalPrice = BigDecimal.valueOf(productQuantity, 2).multiply(productPrice);
    }
}
