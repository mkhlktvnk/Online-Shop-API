package edu.bsuir.sneakersshop.web.model;

import edu.bsuir.sneakersshop.domain.entity.Size;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private SeasonType seasonType;

    private Set<ImageModel> images;

    private Set<Size> sizes;
}
