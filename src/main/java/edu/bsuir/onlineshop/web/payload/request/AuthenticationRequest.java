package edu.bsuir.onlineshop.web.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "username", access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1)
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
