package edu.bsuir.onlineshop.web.link.handler;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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

    private Optional<UserDetails> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of((UserDetails) principal);
        } else {
            return Optional.empty();
        }
    }

    protected abstract void addCommonLinks(T model);

    protected abstract void addUserRelatedLinks(T model);

    protected abstract void addAdminRelatedLinks(T model);
}
