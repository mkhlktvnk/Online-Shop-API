package edu.bsuir.onlineshop.domain.entity.enums;

public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

}
