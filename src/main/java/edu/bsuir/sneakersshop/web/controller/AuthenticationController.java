package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.service.AuthService;
import edu.bsuir.sneakersshop.web.mapper.UserMapper;
import edu.bsuir.sneakersshop.web.model.UserModel;
import edu.bsuir.sneakersshop.web.payload.request.AuthenticationRequest;
import edu.bsuir.sneakersshop.web.payload.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel register(@Valid @RequestBody UserModel userModel) {
        User user = mapper.mapToEntity(userModel);
        return mapper.mapToModel(authService.register(user));
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TokenResponse authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);
    }
}
