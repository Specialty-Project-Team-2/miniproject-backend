package com.sparta.miniproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.miniproject.service.KakaoService;
import com.sparta.miniproject.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/api/user/kakao/callback")
    public LoginResponseDto kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);

        return new LoginResponseDto("로그인에 성공했습니다", token);
    }
}
