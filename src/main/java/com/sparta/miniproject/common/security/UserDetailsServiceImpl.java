package com.sparta.miniproject.common.security;

import com.sparta.miniproject.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = new Member();
        principal.setEmail("mock email"); // TODO MemberRepository 생성되면 추가.
        return new UserDetailsImpl(principal);
    }
}
