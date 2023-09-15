package com.sparta.miniproject.member.controller;

import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.dto.SignupRequestDto;
import com.sparta.miniproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ResponseEntity<MemberResponseDto> signup (@RequestBody SignupRequestDto signupRequestDto, RedirectAttributes redirectAttributes) {
        return ResponseEntity.ok(memberService.signup(signupRequestDto));
    }
}
