package com.sparta.miniproject.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageSourceUtil {
    private final MessageSource source;

    public String interpretErrorMessage(String errorMessage, Object... args) {
        return source.getMessage(errorMessage, args, Locale.getDefault());
    }
}
