package com.sparta.miniproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class FilterChainRingContainer implements FilterChainRing {
    private final Collection<FilterChainRing> filterChainRings;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        for (FilterChainRing ring : filterChainRings) {
            ring.configure(http);
        }
    }
}
