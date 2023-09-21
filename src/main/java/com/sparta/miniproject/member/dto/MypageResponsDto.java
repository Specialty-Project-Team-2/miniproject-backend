package com.sparta.miniproject.member.dto;

import lombok.Getter;

@Getter
public class MypageResponsDto {
    private String email;
    private String nickname;

    public MypageResponsDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
