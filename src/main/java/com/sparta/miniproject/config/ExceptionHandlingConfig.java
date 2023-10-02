package com.sparta.miniproject.config;

import com.sparta.miniproject.exception.AccessDeniedHandlerImpl;
import com.sparta.miniproject.exception.AuthenticationEntryPointImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExceptionHandlingConfig {
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public FilterChainRing configureExceptionHandlingConfig() {
        return http -> http
                .exceptionHandling(b -> b
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
                );
    }
}
