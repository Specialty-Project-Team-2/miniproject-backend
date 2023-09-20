package com.sparta.miniproject.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthorizeHttpRequestsConfig {
    @Bean
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerInCommon() {
        // 인가 경로 설정
        return request -> request
                // 정적 리소스 경로 허용
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
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
