package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(long id);
    User insert(User user);
}
