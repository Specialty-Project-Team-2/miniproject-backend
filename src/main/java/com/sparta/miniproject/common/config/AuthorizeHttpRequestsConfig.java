package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.config.container.CustomizerAnyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
public class AuthorizeHttpRequestsConfig {
    @Bean
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerInCommon() {
        // 인가 경로 설정
        return request -> request
                // Member Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/signup")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/login")).permitAll()

                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/member/*")).permitAll()

                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/api/member/me")).authenticated()

                // Company Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/company/*")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/company")).permitAll()

                // Comment Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/comment/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/comment/**")).authenticated()

                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/company/*/comment")).permitAll()

                // 카카오 인증 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/user/kakao/callback")).permitAll()

                // 정적 리소스 경로 허용
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
    }

    @Bean
    @Profile("local")
    public CustomizerAnyRequest customizerInLocal() {
        return request -> request.anyRequest().permitAll();
    }

    @Bean
    @Profile({"prod"})
    public CustomizerAnyRequest customizerInProd() {
        return request -> request.anyRequest().authenticated();
    }
}
