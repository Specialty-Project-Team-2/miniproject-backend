package com.sparta.miniproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public class JobException extends RuntimeException {
    private final String msg;
    private final HttpStatus status;

    public static JobException from(HttpStatus status, String msg) {
        return new JobException(msg, status);
    }
}
