package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.AuthService;
import edu.bsuir.onlineshop.service.JwtService;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.web.payload.request.AuthenticationRequest;
import edu.bsuir.onlineshop.web.payload.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User register(User user) {
        return userService.insert(user);
    }

    @Override
    public TokenResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        return new TokenResponse(token);
    }
}
