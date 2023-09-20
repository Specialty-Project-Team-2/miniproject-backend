package com.sparta.miniproject.mypage.service;

import com.sparta.miniproject.common.exception.JobException;
import com.sparta.miniproject.member.entity.Member;
import com.sparta.miniproject.member.repository.MemberRepository;
import com.sparta.miniproject.mypage.dto.MypageResponsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MemberRepository memberRepository;

    public MypageResponsDto mypage(Long userid) { //로그한 유저 = 본인 확인하는게 필요할듯함 추후 인가페이지와 함께 예정

        Member member = memberRepository.findById(userid).orElseThrow(
                ()-> JobException.builder()
                        .msg("mypage.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
                );
        return new MypageResponsDto(member.getId(), member.getEmail(), member.getNickname());
    }
}

