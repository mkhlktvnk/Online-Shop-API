package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {
    @JsonProperty("id")
    @NotNull
    @Min(0)
    private Long id;

    @JsonProperty("imageUrl")
    @NotNull
    @NotBlank
    private String imageUrl;
}
