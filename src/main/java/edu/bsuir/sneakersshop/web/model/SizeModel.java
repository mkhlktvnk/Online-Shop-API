package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeModel {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("sizeValue")
    private Integer sizeValue;

    @JsonProperty("quantity")
    private Integer quantity;
}
