package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Role;
import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.domain.entity.enums.RoleType;
import edu.bsuir.sneakersshop.domain.repository.RoleRepository;
import edu.bsuir.sneakersshop.domain.repository.UserRepository;
import edu.bsuir.sneakersshop.service.UserService;
import edu.bsuir.sneakersshop.service.exception.EntityAlreadyExistsException;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.UserMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMessages userMessages;

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(userMessages.getNotFound()));
    }

    @Override
    @Transactional
    public User insert(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(userMessages.getAlreadyExists());
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExistsException(userMessages.getAlreadyExists());
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(userMessages.getAlreadyExists());
        }

        Role role = roleRepository.findByAuthority(RoleType.USER.getRoleName())
                .orElse(roleRepository.save(new Role(RoleType.USER)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singleton(role));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(userMessages.getNotFound()));
    }
}
