package com.sparta.miniproject.mypage.dto;

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
