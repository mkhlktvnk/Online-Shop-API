package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.AuthService;
import edu.bsuir.onlineshop.web.mapper.UserMapper;
import edu.bsuir.onlineshop.web.model.UserModel;
import edu.bsuir.onlineshop.web.payload.request.AuthenticationRequest;
import edu.bsuir.onlineshop.web.payload.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserModel> register(@Valid @RequestBody UserModel userModel) {
        User user = mapper.mapToEntity(userModel);
        User savedUser = authService.register(user);
        UserModel savedUserModel = mapper.mapToModel(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserModel);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        TokenResponse tokenResponse = authService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    }
}
