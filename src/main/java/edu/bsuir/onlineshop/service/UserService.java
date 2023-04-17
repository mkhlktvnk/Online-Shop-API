package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<User> findAll(Pageable pageable);
    User findById(long id);

    User insert(User user);

    boolean existsById(long id);
}
