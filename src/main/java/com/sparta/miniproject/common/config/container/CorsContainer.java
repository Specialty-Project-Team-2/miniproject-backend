package com.sparta.miniproject.common.config.container;

import com.sparta.miniproject.common.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CorsContainer extends CustomizerContainer<CorsConfigurer<HttpSecurity>> {
    public CorsContainer(Collection<Customizer<CorsConfigurer<HttpSecurity>>> elements) {
        super(elements);
    }
}
