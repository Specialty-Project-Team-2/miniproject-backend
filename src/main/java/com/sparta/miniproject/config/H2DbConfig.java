package com.sparta.miniproject.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

@Profile("test")
@Configuration
public class H2DbConfig {
    @Bean
    public Customizer<HeadersConfigurer<HttpSecurity>> headersCustomizer() {
        return builder -> builder.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
    }

    @Bean
    public Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> RequestMatchersCustomizer() {
        return request -> {
            // H2 DB를 위한 설정
            request.requestMatchers(PathRequest.toH2Console()).permitAll();
        };
    }
}
