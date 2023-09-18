package com.sparta.miniproject.common.filter;

import com.sparta.miniproject.tool.ApplicationContextSupplier;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@Slf4j
@ApplicationContextSupplier
@Import(JwtAuthorizationFilter.class)
class JwtAuthorizationFilterTest {
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Test
    @DisplayName("[정상 작동] 쿠키를 집어 넣을 때, 해당 쿠키를 제대로 꺼낼 수 있음.")
    public void getJwtFromRequest() {
        // given
        // 쿠키가 내재된 MockHttpServletRequest
        final String REAL_COOKIE_VALUE = "real one";

        Cookie cookie1 = new Cookie("mock_key_1", "mock value 1");
        Cookie cookie2 = new Cookie("mock_key_1", "mock value 1");
        Cookie cookie3 = new Cookie("Authorization", REAL_COOKIE_VALUE);
        Cookie[] cookies = {cookie1, cookie2, cookie3};

        HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(mockHttpServletRequest.getCookies())
                .thenReturn(cookies);

        // when
        String jwt = jwtAuthorizationFilter.getJwtFromRequest(mockHttpServletRequest);

        // then
        // 많은 쿠키 중에 원하는 쿠키를 가져오는지
        log.info(jwt);
        assertThat(jwt).isEqualTo(REAL_COOKIE_VALUE);
    }

    @Test
    @DisplayName("[정상 작동] UserDetails 가 주어 지면 제대로 SecurityContextHolder 에 넣어둠.")
    public void setPrincipalInSecurityContextHolder() {
        // given
        // MockUserDetails 가 주어 지면
        UserDetails mockUserDetails = Mockito.mock(UserDetails.class);

        // when
        jwtAuthorizationFilter.setPrincipalInSecurityContextHolder(mockUserDetails);

        // then
        // 제대로 SecurityContextHolder 에 넣어둠.
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        log.info(principal.getUsername());
        assertSame(principal, mockUserDetails);
    }
}