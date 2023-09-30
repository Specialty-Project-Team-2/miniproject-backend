package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private final String nickname;

    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "signup.password.not_password_form")
    private final String password;

    @JsonCreator
    public MemberRequestDto(
            String nickname,
            String password
    ) {
        this.nickname = nickname;
        this.password = password;
    }
}
