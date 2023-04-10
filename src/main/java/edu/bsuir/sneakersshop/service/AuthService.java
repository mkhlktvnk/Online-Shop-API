package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.web.payload.request.AuthenticationRequest;
import edu.bsuir.sneakersshop.web.payload.response.TokenResponse;

public interface AuthService {
    User register(User user);

    TokenResponse authenticate(AuthenticationRequest request);
}
