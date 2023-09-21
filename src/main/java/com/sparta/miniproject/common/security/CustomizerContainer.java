package com.sparta.miniproject.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.Customizer;

import java.util.Collection;

@RequiredArgsConstructor
public abstract class CustomizerContainer<T> implements Customizer<T> {
    protected final Collection<Customizer<T>> elements;

    @Override
    public void customize(T arg) {
        for(Customizer<T> element : elements) {
            element.customize(arg);
        }
    }
}
