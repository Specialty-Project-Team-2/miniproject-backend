package com.sparta.miniproject.mypage.controller;

import com.sparta.miniproject.mypage.dto.MypageResponsDto;
import com.sparta.miniproject.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 마이 페이지
    @GetMapping("/mypage/{userid}")
    public ResponseEntity<MypageResponsDto> mypage (@PathVariable Long userid) {
        return ResponseEntity.ok(mypageService.mypage(userid));
    }
}
