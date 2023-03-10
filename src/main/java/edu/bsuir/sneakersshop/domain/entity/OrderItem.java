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
    private Integer productSize;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @PrePersist
    private void calculateTotalPrice() {
        totalPrice = BigDecimal.valueOf(productQuantity, 2).multiply(product.getPrice());
    }
}
