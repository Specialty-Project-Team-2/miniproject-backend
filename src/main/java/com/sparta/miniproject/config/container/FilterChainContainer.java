package com.sparta.miniproject.config.container;

import com.sparta.miniproject.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FilterChainContainer extends CustomizerContainer<HttpSecurity> {
    public FilterChainContainer(Collection<Customizer<HttpSecurity>> elements) {
        super(elements);
    }
}
