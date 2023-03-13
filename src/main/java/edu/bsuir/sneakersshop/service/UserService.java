package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.User;

public interface UserService {
    User findByUsername(String username);
    User insert(User user);
    void delete(String username);
}
