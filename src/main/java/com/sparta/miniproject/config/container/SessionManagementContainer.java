package com.sparta.miniproject.config.container;

import com.sparta.miniproject.security.CustomizerContainer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SessionManagementContainer extends CustomizerContainer<SessionManagementConfigurer<HttpSecurity>> {
    public SessionManagementContainer(Collection<Customizer<SessionManagementConfigurer<HttpSecurity>>> elements) {
        super(elements);
    }
}
