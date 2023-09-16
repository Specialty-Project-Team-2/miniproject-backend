package com.sparta.miniproject.common.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER");

    private final String dbName;
}
