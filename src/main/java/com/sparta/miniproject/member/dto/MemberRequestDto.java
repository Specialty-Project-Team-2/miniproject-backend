package com.sparta.miniproject.member.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "형식에 맞지 않는 비밀번호 입니다")
    private String password;

}
