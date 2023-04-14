package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel extends RepresentationModel<ProductModel> {

    @NotNull
    @Min(0)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 255)
    @JsonProperty("topic")
    private String topic;

    @NotNull
    @NotBlank
    @Length(max = 10000)
    @JsonProperty("content")
    private String content;

    @NotNull
    @Min(1)
    @Max(5)
    @JsonProperty("mark")
    private Integer mark;
}
