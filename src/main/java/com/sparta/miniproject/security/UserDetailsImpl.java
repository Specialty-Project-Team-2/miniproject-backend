package com.sparta.miniproject.security;

import com.sparta.miniproject.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 아직 로그인 유저로서의 권한만 있으면 되서 간단하게 처리함.
        SimpleGrantedAuthority authorityOfUser = new SimpleGrantedAuthority(RoleType.USER.getDbName());
        return List.of(authorityOfUser);
    }

    public Member getMember() {
        return member;
    }
    @Override
    public String getPassword() {
        // 로그인 과정을 직접 구현하기 때문에 필요 없음.
//        return "";
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
