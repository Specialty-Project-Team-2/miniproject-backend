package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.request.LoginRequestDto;
import com.sparta.miniproject.dto.request.MemberRequestDto;
import com.sparta.miniproject.dto.request.SignupRequestDto;
import com.sparta.miniproject.dto.response.LoginResponseDto;
import com.sparta.miniproject.dto.response.MemberResponseDto;
import com.sparta.miniproject.dto.response.MypageResponsDto;
import com.sparta.miniproject.entity.Member;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.utils.MessageSourceUtil;
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
            throw JobException.from(HttpStatus.BAD_REQUEST, "signup.nickname.same");
        }

        String email = signupRequestDto.getEmail();
        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw JobException.from(HttpStatus.BAD_REQUEST, "signup.email.same");
        }

        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String message = source.interpretErrorMessage("signup.success");
        return MemberResponseDto.fromMsg(message);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> JobException.from(HttpStatus.UNAUTHORIZED, "login.email.not_same")
        );

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw JobException.from(HttpStatus.UNAUTHORIZED, "login.password.not_same");
        }

        String token = jwtUtil.createToken(member);

        String message = source.interpretErrorMessage("login.success");
        return new LoginResponseDto(message, token);
    }

    public MypageResponsDto mypage(Long memberid) {

        Member member = memberRepository.findById(memberid).orElseThrow(
                () -> JobException.from(HttpStatus.BAD_REQUEST, "mypage.not_found")
        );
        return MypageResponsDto.fromEntity(member);
    }

    @Transactional
    public MypageResponsDto mypageUpdate(MemberRequestDto memberRequestDto, Member memberLoggedIn) {
        memberRequestDto.update(memberLoggedIn, passwordEncoder);
        memberRepository.save(memberLoggedIn);
        return MypageResponsDto.fromEntity(memberLoggedIn);
    }
}


