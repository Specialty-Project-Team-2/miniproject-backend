package com.sparta.miniproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.miniproject.dto.request.LoginRequestDto;
import com.sparta.miniproject.dto.response.LoginResponseDto;
import com.sparta.miniproject.dto.response.MemberResponseDto;
import com.sparta.miniproject.dto.request.SignupRequestDto;
import com.sparta.miniproject.utils.MessageSourceUtil;
import com.sparta.miniproject.service.KakaoService;
import com.sparta.miniproject.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final MessageSourceUtil source;

    @PostMapping("/api/signup")
    public MemberResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return memberService.signup(signupRequestDto);
    }

    @PostMapping("/api/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return memberService.login(loginRequestDto, res);
    }

    @GetMapping("/api/user/kakao/callback")
    public LoginResponseDto kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);

        String message = source.interpretErrorMessage("login.success");
        return new LoginResponseDto(message, token);
    }
}
