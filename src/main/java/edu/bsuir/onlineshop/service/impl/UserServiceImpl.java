package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Role;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import edu.bsuir.onlineshop.domain.repository.RoleRepository;
import edu.bsuir.onlineshop.domain.repository.UserRepository;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.service.exception.EntityAlreadyExistsException;
import edu.bsuir.onlineshop.service.exception.EntityNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessagesSource messages;

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage(MessageKey.USER_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    public User insert(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_EMAIL,
                            user.getEmail())
            );
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_USERNAME,
                            user.getUsername())
            );
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_PHONE_NUMBER,
                            user.getUsername())
            );
        }

        Role role = roleRepository.findByAuthority(RoleType.USER.getRoleName())
                .orElseGet(() -> roleRepository.save(new Role(RoleType.USER)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singleton(role));

        return userRepository.save(user);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("user.not-found")
                ));
    }
}
