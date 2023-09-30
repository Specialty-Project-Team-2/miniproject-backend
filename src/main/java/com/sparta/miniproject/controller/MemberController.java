package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.request.MemberRequestDto;
import com.sparta.miniproject.dto.response.MypageResponsDto;
import com.sparta.miniproject.service.MemberService;
import com.sparta.miniproject.entity.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/member/{memberid}")
    public MypageResponsDto readById(@PathVariable Long memberid) {
        return memberService.mypage(memberid);
    }

    @PutMapping("/api/member/me")
    public MypageResponsDto update(
            @Valid @RequestBody MemberRequestDto memberRequestDto,
            @AuthenticationPrincipal Member principal
    ) {
        return memberService.mypageUpdate(memberRequestDto, principal);
    }
}