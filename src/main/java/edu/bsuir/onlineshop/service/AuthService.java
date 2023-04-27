package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.web.payload.request.AuthenticationRequest;
import edu.bsuir.onlineshop.web.payload.response.TokenResponse;

public interface AuthService {
    User register(User user);

    TokenResponse authenticate(AuthenticationRequest request);
}
