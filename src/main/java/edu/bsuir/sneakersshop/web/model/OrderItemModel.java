package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

    @NotNull
    @Min(1)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @JsonProperty(value = "totalPrice", access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalPrice;

    @NotNull
    @JsonProperty(value = "productQuantity", access = JsonProperty.Access.READ_ONLY)
    private Integer productQuantity;

    @NotNull
    @JsonProperty(value = "productSize", access = JsonProperty.Access.READ_ONLY)
    private Integer productSize;

    @NotNull
    @JsonProperty(value = "product", access = JsonProperty.Access.READ_ONLY)
    private ProductModel productModel;

}
