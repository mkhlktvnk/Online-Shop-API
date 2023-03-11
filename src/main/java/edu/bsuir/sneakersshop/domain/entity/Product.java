package edu.bsuir.sneakersshop.domain.entity;

import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeasonType seasonType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Image> images = new HashSet<>();
}
