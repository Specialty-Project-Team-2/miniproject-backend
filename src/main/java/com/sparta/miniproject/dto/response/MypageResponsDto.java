package com.sparta.miniproject.dto.response;

import com.sparta.miniproject.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class MypageResponsDto {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String password;

    private MypageResponsDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.password = entity.getPassword();
    }

    public static MypageResponsDto fromEntity(Member entity) {
        return new MypageResponsDto(entity);
    }
}
