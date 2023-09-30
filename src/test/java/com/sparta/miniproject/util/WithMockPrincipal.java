package com.sparta.miniproject.util;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockPrincipalSecurityContextFactory.class)
public @interface WithMockPrincipal {
    String email() default "iksadnorth@gmail.com";
    String password() default "q1w2e3r4";
    String nickname() default "iksad";
    long kakaoId() default 0L;
}
