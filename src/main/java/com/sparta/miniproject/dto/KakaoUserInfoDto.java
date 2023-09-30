package com.sparta.miniproject.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

    private KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public static KakaoUserInfoDto fromJson(JsonNode json) {
        Long id = json.get("id")
                .asLong();
        String nickname = json.get("properties").get("nickname")
                .asText();
        String email = json.get("kakao_account").get("email")
                .asText();
        return new KakaoUserInfoDto(id, nickname, email);
    }
}
