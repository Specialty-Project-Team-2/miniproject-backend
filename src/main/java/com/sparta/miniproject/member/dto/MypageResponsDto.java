package com.sparta.miniproject.member.dto;

import lombok.Getter;

@Getter
public class MypageResponsDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;

    public MypageResponsDto(long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public MypageResponsDto(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }
}
