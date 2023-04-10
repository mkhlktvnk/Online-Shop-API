package edu.bsuir.sneakersshop.web.criteria;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductCriteria {

    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String description;

    @Min(1)
    @Digits(integer = 40, fraction = 2)
    private BigDecimal minPrice;

    @Min(1)
    @Digits(integer = 40, fraction = 2)
    private BigDecimal maxPrice;

    @Size(min = 1)
    private String brandName;
}
