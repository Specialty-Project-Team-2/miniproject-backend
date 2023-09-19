package com.sparta.miniproject.common.config.chain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class AuthorizeHttpRequestsFilterChain {
    @Bean
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerInCommon() {
        // 인가 경로 설정
        return request -> request
                // Member Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/signup")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/login")).permitAll()

                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/mypage/*")).permitAll()

                // Company Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/detail/*")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/")).permitAll()

                // Comment Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/comment/**")).authenticated();
    }

    @Bean
    @Profile("local")
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerInLocal() {
        return request -> request.anyRequest().permitAll();
    }

    @Bean
    @Profile({"prod"})
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerInProd() {
        return request -> request.anyRequest().authenticated();
    }
}