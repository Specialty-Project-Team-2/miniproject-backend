package com.sparta.miniproject.kakao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.miniproject.common.jwtutil.JwtUtil;
import com.sparta.miniproject.kakao.service.KakaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=7af57035200ce2da34864e794371c7db&redirect_uri=http://localhost:8080/api/user/kakao/callback
    @GetMapping("/api/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
