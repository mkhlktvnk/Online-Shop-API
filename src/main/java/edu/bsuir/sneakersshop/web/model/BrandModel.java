package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandModel {
    @JsonProperty("id")
    @Min(1)
    @NotNull
    private Long id;

    @JsonProperty("name")
    @NotNull
    @NotBlank
    @Length(max = 255)
    private String name;

    @JsonProperty("description")
    @NotNull
    @NotBlank
    @Length(max = 5000)
    private String description;
}
