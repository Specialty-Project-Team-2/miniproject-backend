package com.sparta.miniproject.common.filter;

import com.sparta.miniproject.common.jwtutil.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.sparta.miniproject.common.jwtutil.JwtUtil.AUTHORIZATION_HEADER;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String token = getJwtFromRequest(request);
            System.out.println(request.getHeader(AUTHORIZATION_HEADER));
            System.out.println(token);
            token = jwtUtil.substringToken(token);

            if(!jwtUtil.validateToken(token)) {
                throw new IllegalAccessError();
            }

            Claims claims = jwtUtil.getUserInfoFromToken(token);
            String username = claims.getSubject();
            UserDetails principal = userDetailsService.loadUserByUsername(username);
            setPrincipalInSecurityContextHolder(principal);

        } catch (NullPointerException | IllegalAccessError ex) {
            // 토큰 해석 실패는 있을 수 있는 일이므로 별다른 처리는 하지 않음.

        } finally {
            filterChain.doFilter(request, response);

        }
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public void setPrincipalInSecurityContextHolder(UserDetails principal) {
        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        principal, "", principal.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
