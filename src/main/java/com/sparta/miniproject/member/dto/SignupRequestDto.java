package com.sparta.miniproject.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank (message = "이메일을 입력해 주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, max = 15, message = "비밀번호는 6자 이상 15자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자(!@#$%?)를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickname;
}
