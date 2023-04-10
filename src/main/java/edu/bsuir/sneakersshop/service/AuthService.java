package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.web.request.AuthenticationRequest;
import edu.bsuir.sneakersshop.web.response.TokenResponse;

public interface AuthService {
    User register(User user);

    TokenResponse authenticate(AuthenticationRequest request);
}
