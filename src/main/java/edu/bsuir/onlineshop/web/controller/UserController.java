package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.web.mapper.UserMapper;
import edu.bsuir.onlineshop.web.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PagedResourcesAssembler<User> pagedResourcesAssembler;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @GetMapping("/users")
    public ResponseEntity<PagedModel<UserModel>> findAll(@PageableDefault Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        PagedModel<UserModel> page = pagedResourcesAssembler.toModel(users, mapper::mapToModel);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserModel> findByUsername(@PathVariable String username) {
        User user = (User) userService.loadUserByUsername(username);
        UserModel userModel = mapper.mapToModel(user);
        return ResponseEntity.ok(userModel);
    }
}
