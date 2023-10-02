package com.sparta.miniproject.util;

import com.sparta.miniproject.exception.GlobalExceptionControllerAdvice;
import com.sparta.miniproject.utils.MessageSourceUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({GlobalExceptionControllerAdvice.class, MessageSourceUtil.class})
public @interface EnableGlobalExceptionControllerAdviceTest {
}
