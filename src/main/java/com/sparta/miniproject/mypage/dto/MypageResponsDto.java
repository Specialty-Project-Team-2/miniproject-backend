package com.sparta.miniproject.mypage.dto;

import lombok.Getter;

@Getter
public class MypageResponsDto {
    private Long id;
    private String email;
    private String nickname;

    public MypageResponsDto(long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
