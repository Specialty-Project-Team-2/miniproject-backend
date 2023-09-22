package com.sparta.miniproject.kakao.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter @NoArgsConstructor
public class KakaoOAuth2Properties {
    @Value("${miniproject.oauth2.kakao.client-id}")
    private String clientId;
    @Value("${miniproject.oauth2.kakao.redirect-url}")
    private String redirectUrl;
}
