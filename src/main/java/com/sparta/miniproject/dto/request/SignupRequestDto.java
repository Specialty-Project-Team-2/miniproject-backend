package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank(message = "signup.email.empty_input")
    @Email(message = "signup.email.not_email_form")
    private final String email;

    @Size(min = 6, max = 15, message = "signup.password.inapposite_size")
    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "signup.password.not_password_form")
    private final String password;

    @NotBlank(message = "signup.nickname.empty_input")
    private final String nickname;

    @JsonCreator
    public SignupRequestDto(
            String email,
            String password,
            String nickname
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
