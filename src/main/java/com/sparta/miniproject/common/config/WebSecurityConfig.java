package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.exception.AccessDeniedHandlerImpl;
import com.sparta.miniproject.common.exception.AuthenticationEntryPointImpl;
import com.sparta.miniproject.common.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 인가 경로 설정
        AuthorizeHttpRequestsConfig.setAuthorizeHttpRequestsInLocal(http);

        // JWT 적용을 위한 설정
        http.sessionManagement(b -> b
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // csrf 설정
        http.csrf(AbstractHttpConfigurer::disable);

        // 인가 에러 핸들러 설정
        http.exceptionHandling(b -> b
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
        );

        // h2 console 사용
        http.headers(
                b -> b
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        return http.build();
    }
}
