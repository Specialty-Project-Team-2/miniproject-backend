package com.sparta.miniproject.tool;

import com.sparta.miniproject.common.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.member.filter.LoginFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest(
        controllers = MockController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                JwtAuthorizationFilter.class,
                                LoginFilter.class
                        })
        }
)
@EnableGlobalExceptionControllerAdviceTest
public @interface ApplicationContextSupplier {
}
