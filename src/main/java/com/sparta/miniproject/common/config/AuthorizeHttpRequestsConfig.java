package com.sparta.miniproject.common.config;

import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@UtilityClass
public class AuthorizeHttpRequestsConfig {

    private void setAuthorizeHttpRequestsInCommon(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request) {
        // 인가 경로 설정
        request
                // Company Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/detail/*")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/")).permitAll()

                // Comment Entity 관련 API
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/comment/**")).authenticated()

                // h2 console 사용
                .requestMatchers(PathRequest.toH2Console()).permitAll();
    }

    public void setAuthorizeHttpRequestsInLocal(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
            // 인가 경로 공통 설정
            setAuthorizeHttpRequestsInCommon(request);

            // 개발을 위해 미지의 요청에 대해 모두 허용
            request.anyRequest().permitAll();
        });
    }

    public void setAuthorizeHttpRequestsInProd(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
            // 인가 경로 공통 설정
            setAuthorizeHttpRequestsInCommon(request);

            // 운영을 위해 미지의 요청에 대해 모두 로그인 유저만 접근 가능
            request.anyRequest().authenticated();
        });
    }
}
