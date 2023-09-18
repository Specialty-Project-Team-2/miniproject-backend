package com.sparta.miniproject.tool;

import com.sparta.miniproject.common.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.common.jwtutil.JwtUtil;
import com.sparta.miniproject.common.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        JwtAuthorizationFilter.class,
        UserDetailsServiceImpl.class,
        JwtUtil.class
})
public @interface EnableJwtAuthorizationFilterTest {
}
