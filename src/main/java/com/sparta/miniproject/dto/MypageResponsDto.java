package com.sparta.miniproject.dto;

import com.sparta.miniproject.entity.Member;
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

    public MypageResponsDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.password = entity.getPassword();
    }
}
