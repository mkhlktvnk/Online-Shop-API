package edu.bsuir.sneakersshop.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp placedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @PrePersist
    void calculateTotalPrice() {
        totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
