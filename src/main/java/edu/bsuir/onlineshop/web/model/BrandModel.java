package edu.bsuir.onlineshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "brand", collectionRelation = "brands")
public class BrandModel extends RepresentationModel<BrandModel> {

    @Min(1)
    @NotNull
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 255)
    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @Length(max = 5000)
    @JsonProperty("description")
    private String description;
}
