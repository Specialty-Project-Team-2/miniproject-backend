package com.sparta.miniproject.tool;

import com.sparta.miniproject.common.exception.GlobalExceptionControllerAdvice;
import com.sparta.miniproject.common.exception.MessageSourceUtil;
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
