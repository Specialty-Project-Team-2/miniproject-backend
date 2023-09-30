package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.*;
import com.sparta.miniproject.entity.Member;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.exception.MessageSourceUtil;
import com.sparta.miniproject.repository.MemberRepository;
import com.sparta.miniproject.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final MessageSourceUtil source;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<Member> checkNickname = memberRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw JobException.builder()
                    .msg("signup.nickname.same")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        String email = signupRequestDto.getEmail();
        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw JobException.builder()
                    .msg("signup.email.same")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String message = source.interpretErrorMessage("signup.success");
        return new MemberResponseDto(message);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> JobException.builder()
                        .msg("login.email.not_same")
                        .status(HttpStatus.UNAUTHORIZED)
                        .build()
        );

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw JobException.builder()
                    .msg("login.password.not_same")
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        String token = jwtUtil.createToken(member);

        String message = source.interpretErrorMessage("login.success");
        return new LoginResponseDto(message, token);
    }

    public MypageResponsDto mypage(Long memberid) {

        Member member = memberRepository.findById(memberid).orElseThrow(
                () -> JobException.builder()
                        .msg("mypage.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        return new MypageResponsDto(member.getId(), member.getEmail(), member.getNickname());
    }

    @Transactional
    public MypageResponsDto mypageUpdate(MemberRequestDto memberRequestDto, Member memberLoggedIn) {
        memberLoggedIn.update(memberRequestDto, passwordEncoder);
        memberRepository.save(memberLoggedIn);
        return new MypageResponsDto(memberLoggedIn);
    }
}


