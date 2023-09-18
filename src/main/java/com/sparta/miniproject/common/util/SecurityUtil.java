package com.sparta.miniproject.common.util;

import com.sparta.miniproject.common.security.UserDetailsImpl;
import com.sparta.miniproject.member.entity.Member;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@UtilityClass
public class SecurityUtil {
    public Optional<UserDetails> getPrincipal() {
        return Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserDetails.class::cast);
    }

    public Optional<Member> getMemberLoggedIn() {
        return SecurityUtil.getPrincipal()
                .map(UserDetailsImpl.class::cast)
                .map(UserDetailsImpl::getMember);
    }
}
