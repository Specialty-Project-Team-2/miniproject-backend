package com.sparta.miniproject.common.config.chain.container;

import com.sparta.miniproject.common.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class HeadersContainer extends CustomizerContainer<HeadersConfigurer<HttpSecurity>> {
    public HeadersContainer(Collection<Customizer<HeadersConfigurer<HttpSecurity>>> elements) {
        super(elements);
    }
}
