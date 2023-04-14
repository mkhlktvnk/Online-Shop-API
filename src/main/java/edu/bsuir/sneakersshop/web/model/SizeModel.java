package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
@Relation(itemRelation = "size", collectionRelation = "sizes")
public class SizeModel extends RepresentationModel<SizeModel> {
    @NotNull
    @Min(0)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Min(1)
    @JsonProperty("sizeValue")
    private Integer sizeValue;

    @NotNull
    @Min(0)
    @JsonProperty("quantity")
    private Integer quantity;
}
