package edu.bsuir.sneakersshop.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long productId;

    @NotNull
    @Min(1)
    private Integer productSize;

    @NotNull
    @Min(1)
    private Integer productQuantity;
}
