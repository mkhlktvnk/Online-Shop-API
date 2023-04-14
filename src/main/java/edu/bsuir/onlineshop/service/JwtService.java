package edu.bsuir.onlineshop.service;

public interface JwtService {

    String generateToken(String login);

    boolean isTokenValid(String token);

    String getLoginFromToken(String token);

}
