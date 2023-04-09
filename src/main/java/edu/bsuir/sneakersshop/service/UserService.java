package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.User;

public interface UserService {
    User findById(long id);
}
