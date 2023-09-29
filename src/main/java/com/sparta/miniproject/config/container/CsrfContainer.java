package com.sparta.miniproject.config.container;

import com.sparta.miniproject.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CsrfContainer extends CustomizerContainer<CsrfConfigurer<HttpSecurity>> {
    public CsrfContainer(Collection<Customizer<CsrfConfigurer<HttpSecurity>>> elements) {
        super(elements);
    }
}
