package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.exception.AccessDeniedHandlerImpl;
import com.sparta.miniproject.common.exception.AuthenticationEntryPointImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;

@Configuration
@RequiredArgsConstructor
public class ExceptionHandlingConfig {
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public Customizer<ExceptionHandlingConfigurer<HttpSecurity>> exceptionHandlingCustomizer() {
        return b -> b
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }
}
