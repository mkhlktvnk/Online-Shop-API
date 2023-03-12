package edu.bsuir.sneakersshop.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeModel {
    private Long id;
    private Integer sizeValue;
    private Integer quantity;
}
