package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT 적용을 위한 설정
 */
@Configuration
@RequiredArgsConstructor
public class JwtConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementCustomizer() {
        return configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public Customizer<HttpSecurity> addFilterBefore() {
        return http -> http
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer() {
        return AbstractHttpConfigurer::disable;
    }
}
