package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @NotNull
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @JsonProperty(value = "totalPrice", access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalPrice;

    @NotNull
    @JsonProperty(value = "orderItem", access = JsonProperty.Access.READ_ONLY)
    private OrderItemModel orderItemModel;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty(value = "purchasedAt", access = JsonProperty.Access.READ_ONLY)
    private String placedAt;

}
