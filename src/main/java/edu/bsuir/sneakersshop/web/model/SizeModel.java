package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Min(0)
    private Long id;

    @JsonProperty("sizeValue")
    @NotNull
    @Min(1)
    private Integer sizeValue;

    @JsonProperty("quantity")
    @NotNull
    @Min(0)
    private Integer quantity;
}
