package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "category", collectionRelation = "categories")
public class CategoryModel extends RepresentationModel<CategoryModel> {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;
}
