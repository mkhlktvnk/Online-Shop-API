package edu.bsuir.sneakersshop.service;

import org.springframework.stereotype.Service;

public interface JwtService {

    String generateToken(String login);

    boolean isTokenValid(String token);

    String getLoginFromToken(String token);

}
