package edu.bsuir.sneakersshop.web.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductCriteria {
    private String name;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String brandName;
}
