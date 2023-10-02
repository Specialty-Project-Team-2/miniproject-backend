package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    private final String email;
    private final String password;

    @JsonCreator
    public LoginRequestDto(
            String email,
            String password
    ) {
        this.email = email;
        this.password = password;
    }
}
