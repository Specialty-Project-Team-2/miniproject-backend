package com.sparta.miniproject.member.controller;

import com.sparta.miniproject.member.dto.LoginResponseDto;
import com.sparta.miniproject.member.dto.LoginRequestDto;
import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.dto.SignupRequestDto;
import com.sparta.miniproject.member.service.MemberService;
import com.sparta.miniproject.member.dto.MypageResponsDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // 로그인 페이지
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
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return ResponseEntity.ok(memberService.login(loginRequestDto, res));
    }

    // 마이 페이지
    @GetMapping("/mypage/{memberid}")
    public ResponseEntity<MypageResponsDto> mypage (@PathVariable Long memberid) {
        return ResponseEntity.ok(memberService.mypage(memberid));
    }
}