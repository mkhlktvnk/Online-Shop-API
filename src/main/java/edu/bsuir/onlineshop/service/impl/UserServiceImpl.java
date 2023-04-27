package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Role;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import edu.bsuir.onlineshop.domain.repository.RoleRepository;
import edu.bsuir.onlineshop.domain.repository.UserRepository;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.service.exception.ResourceAlreadyPresentException;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessagesSource messages;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.USER_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    public User insert(User user) {
        checkUserExistsByEmail(user.getEmail());
        checkUserExistsByUsername(user.getUsername());
        checkUserExistsByPhoneNumber(user.getPhoneNumber());

        Role role = roleRepository.findByAuthority(RoleType.USER.name())
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.USER_NOT_FOUND_BY_USERNAME, username)
                ));
    }

    private void checkUserExistsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceAlreadyPresentException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_EMAIL, email)
            );
        }
    }

    private void checkUserExistsByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ResourceAlreadyPresentException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_USERNAME, username)
            );
        }
    }

    private void checkUserExistsByPhoneNumber(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResourceAlreadyPresentException(
                    messages.getMessage(MessageKey.USER_ALREADY_EXISTS_WITH_PHONE_NUMBER, phoneNumber)
            );
        }
    }
}
