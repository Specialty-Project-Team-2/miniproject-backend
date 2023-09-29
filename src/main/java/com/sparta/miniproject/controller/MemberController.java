package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.*;
import com.sparta.miniproject.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    @GetMapping("/api/member/{memberid}")
    public ResponseEntity<MypageResponsDto> mypage (@PathVariable Long memberid) {
        return ResponseEntity.ok(memberService.mypage(memberid));
    }

    // 회원 닉네임 수정
    @PutMapping("/api/member/me")
    public ResponseEntity<MypageResponsDto> mypageUpdate (@Valid @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.mypageUpdate(memberRequestDto));
    }
}