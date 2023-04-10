package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Role;
import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.domain.entity.enums.RoleType;
import edu.bsuir.sneakersshop.domain.repository.RoleRepository;
import edu.bsuir.sneakersshop.domain.repository.UserRepository;
import edu.bsuir.sneakersshop.service.UserService;
import edu.bsuir.sneakersshop.service.exception.EntityAlreadyExistsException;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.MessagesSource;
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
                        messages.getMessage("user.not-found.message")
                ));
    }

    @Override
    @Transactional
    public User insert(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage("user.not-found.message")
            );
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage("user.not-found.message")
            );
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(
                    messages.getMessage("user.not-found.message")
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
                        messages.getMessage("user.not-found.message")
                ));
    }
}
