package com.sparta.miniproject.common.config.chain.container;

import com.sparta.miniproject.common.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ExceptionHandlingContainer extends CustomizerContainer<ExceptionHandlingConfigurer<HttpSecurity>> {
    public ExceptionHandlingContainer(Collection<Customizer<ExceptionHandlingConfigurer<HttpSecurity>>> elements) {
        super(elements);
    }
}
