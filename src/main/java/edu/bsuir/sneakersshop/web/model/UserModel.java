package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Relation(itemRelation = "user", collectionRelation = "users")
public class UserModel extends RepresentationModel<UserModel> {

    @JsonProperty(value =  "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "username")
    private String username;

    @NotNull
    @Email
    @JsonProperty(value = "email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8)
    @JsonProperty(value = "password")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "address")
    private String address;

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "postIndex")
    private String postIndex;
}
