package edu.bsuir.onlineshop.web.link.handler;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public abstract class LinkHandler<T extends RepresentationModel<T>> {
    public void addLinks(T model) {
        addCommonLinks(model);
        getCurrentUser().ifPresent(user -> {
            if (user.getAuthorities().stream().anyMatch(authority ->
                    authority.getAuthority().equals(RoleType.USER.name()))) {
                addUserRelatedLinks(model);
            } else if (user.getAuthorities().stream().anyMatch(authority ->
                    authority.getAuthority().equals(RoleType.ADMIN.name()))) {
                addAdminRelatedLinks(model);
            }
        });
    }

    private Optional<User> getCurrentUser() {
        return Optional.of(
                (User) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()
        );
    }

    protected abstract void addCommonLinks(T model);

    protected abstract void addUserRelatedLinks(T model);

    protected abstract void addAdminRelatedLinks(T model);
}
