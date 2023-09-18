package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.config.chain.container.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final SessionManagementContainer sessionManagementContainer;
    private final CsrfContainer csrfContainer;
    private final ExceptionHandlingContainer exceptionHandlingContainer;
    private final AuthorizeHttpRequestsContainer authorizeHttpRequestsContainer;
    private final HeadersContainer headersContainer;
    private final FilterChainContainer filterChainContainer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagementContainer);
        http.csrf(csrfContainer);
        http.exceptionHandling(exceptionHandlingContainer);
        http.authorizeHttpRequests(authorizeHttpRequestsContainer);
        http.headers(headersContainer);

        filterChainContainer.customize(http);

        return http.build();
    }
}
