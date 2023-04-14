package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(long id);

    User insert(User user);

    boolean existsById(long id);
}
