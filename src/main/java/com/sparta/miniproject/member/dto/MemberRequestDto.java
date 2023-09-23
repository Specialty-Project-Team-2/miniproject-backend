package com.sparta.miniproject.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, max = 15, message = "비밀번호는 6자 이상 15자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "형식에 맞지 않는 비밀번호 입니다")
    private String password;

}
