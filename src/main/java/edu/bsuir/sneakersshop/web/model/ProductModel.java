package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Min(0)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 255)
    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @Length(max = 255)
    @JsonProperty("description")
    private String description;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 50, fraction = 2)
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @NotBlank
    @JsonProperty("seasonType")
    private SeasonType seasonType;

    @JsonProperty("images")
    private Set<ImageModel> images;

    @JsonProperty("sizes")
    private Set<SizeModel> sizes;
}
