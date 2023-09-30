package com.sparta.miniproject.config;

import com.sparta.miniproject.properties.ServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Profile({"test", "prod"})
@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    private final ServerProperties serverProperties;

    @Bean
    public FilterChainRing configureCorsConfig(
            Customizer<CorsConfigurer<HttpSecurity>> corsConfigurerCustomizer
    ) {
        return http -> http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                )

                .cors(corsConfigurerCustomizer);
    }

    @Bean
    @Profile("test")
    public Customizer<CorsConfigurer<HttpSecurity>> corsConfigurationSourceForTest() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(
                serverProperties.getFrontendServerUrl(),
                "http://localhost:3000"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return builder -> builder.configurationSource(source);
    }

    @Bean
    @Profile("prod")
    public Customizer<CorsConfigurer<HttpSecurity>> corsConfigurationSourceForProd() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(
                serverProperties.getFrontendServerUrl()
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return builder -> builder.configurationSource(source);
    }
}
