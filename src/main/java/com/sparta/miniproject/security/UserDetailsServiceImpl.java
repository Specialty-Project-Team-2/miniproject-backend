package com.sparta.miniproject.security;

import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.entity.Member;
import com.sparta.miniproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByEmail(username)
                .orElseThrow(() -> JobException.from(HttpStatus.NOT_FOUND, "login.email.not_same"));
        return new UserDetailsImpl(principal);
    }
}
