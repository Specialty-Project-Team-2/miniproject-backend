package com.sparta.miniproject.member.service;

import com.sparta.miniproject.common.jwtutil.JwtUtil;
import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.dto.SignupRequestDto;
import com.sparta.miniproject.member.entity.Member;
import com.sparta.miniproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<Member> checkNicname = memberRepository.findByNickname(nickname);
        if (checkNicname.isPresent()) {
            throw new IllegalArgumentException();
        }

        String email = signupRequestDto.getEmail();
        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException();
        }

        Member member = new Member(email, password, nickname);
        memberRepository.save(member);
        return new MemberResponseDto("회원 가입을 축하합니다");
    }
}


