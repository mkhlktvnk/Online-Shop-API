package edu.bsuir.sneakersshop.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    @NotNull
    @NotBlank
    @JsonProperty(value = "token", access = JsonProperty.Access.READ_ONLY)
    private String token;

}
