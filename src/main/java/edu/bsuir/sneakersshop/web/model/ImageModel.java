package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "image", collectionRelation = "images")
public class ImageModel extends RepresentationModel<CategoryModel> {

    @NotNull
    @Min(0)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotBlank
    @JsonProperty(value = "imageUrl")
    private String imageUrl;
}
