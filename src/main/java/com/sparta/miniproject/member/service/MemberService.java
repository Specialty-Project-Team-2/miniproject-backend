package com.sparta.miniproject.member.service;

import com.sparta.miniproject.common.exception.JobException;
import com.sparta.miniproject.common.jwtutil.JwtUtil;
import com.sparta.miniproject.member.dto.LoginResponseDto;
import com.sparta.miniproject.member.dto.LoginRequestDto;
import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.dto.MypageResponsDto;
import com.sparta.miniproject.member.dto.SignupRequestDto;
import com.sparta.miniproject.member.entity.Member;
import com.sparta.miniproject.member.repository.MemberRepository;
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
        return new MemberResponseDto("회원 가입을 축하합니다");
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                ()-> JobException.builder()
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

        String token = jwtUtil.createToken(member) ;
        return new LoginResponseDto("로그인을 축하합니다", token);
    }

    public MypageResponsDto mypage(Long memberid) {

        Member member = memberRepository.findById(memberid).orElseThrow(
                ()-> JobException.builder()
                        .msg("mypage.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        return new MypageResponsDto(member.getId(), member.getEmail(), member.getNickname());
    }
}


