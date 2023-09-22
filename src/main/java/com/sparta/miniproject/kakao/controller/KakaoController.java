package com.sparta.miniproject.kakao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.miniproject.kakao.service.KakaoService;
import com.sparta.miniproject.member.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=7af57035200ce2da34864e794371c7db&redirect_uri=http://localhost:8080/api/user/kakao/callback
    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=7af57035200ce2da34864e794371c7db&redirect_uri=http://3.36.132.42:8080/api/user/kakao/callback

    @GetMapping("/api/user/kakao/callback")
    public LoginResponseDto kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);

        return new LoginResponseDto("로그인에 성공했습니다", token);
    }
}
