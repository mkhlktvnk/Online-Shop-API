package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @JsonProperty("id")
    @NotNull
    @Min(0)
    private Long id;

    @JsonProperty("name")
    @NotNull
    @NotBlank
    @Length(max = 255)
    private String name;

    @JsonProperty("description")
    @NotNull
    @NotBlank
    @Length(max = 255)
    private String description;

    @JsonProperty("price")
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 50, fraction = 2)
    private BigDecimal price;

    @JsonProperty("season")
    @NotNull
    @NotBlank
    private SeasonType seasonType;

    @JsonProperty("images")
    private Set<ImageModel> images;

    @JsonProperty("sizes")
    private Set<SizeModel> sizes;
}
