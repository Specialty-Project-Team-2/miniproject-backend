package com.sparta.miniproject.member.controller;

import com.sparta.miniproject.member.dto.LoginRequestDto;
import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.dto.SignupRequestDto;
import com.sparta.miniproject.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signuppage() {
        return "signup";
    }



    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<MemberResponseDto> signup (@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(memberService.signup(signupRequestDto));
    }

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<MemberResponseDto> login (@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return ResponseEntity.ok(memberService.login(loginRequestDto, res));
    }
}
