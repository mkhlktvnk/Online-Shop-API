package edu.bsuir.onlineshop.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
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
@Relation(itemRelation = "review", collectionRelation = "reviews")
public class ReviewModel extends RepresentationModel<ReviewModel> {

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

    @JsonIgnore
    private ProductModel productModel;

    @JsonIgnore
    private UserModel userModel;
}
