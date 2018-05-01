package com.scaffold.application.constant;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private final String userRole;

    UserRole(String role) {
        this.userRole = role;
    }

    public String getUserRole() {
        return this.userRole;
    }
}
