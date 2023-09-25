package com.sparta.miniproject.common.config.container;

import com.sparta.miniproject.common.security.CustomizerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorizeHttpRequestsContainer extends CustomizerContainer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    private final CustomizerAnyRequest customizerAnyRequest;

    @Autowired
    public AuthorizeHttpRequestsContainer(Collection<Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>> elements, CustomizerAnyRequest customizerAnyRequest) {
        super(elements);
        this.customizerAnyRequest = customizerAnyRequest;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry arg) {
        super.customize(arg);
        customizerAnyRequest.accept(arg);
    }
}
