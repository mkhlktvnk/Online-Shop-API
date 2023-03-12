package edu.bsuir.sneakersshop.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "sizes")
@Builder
@RequiredArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer sizeValue;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
