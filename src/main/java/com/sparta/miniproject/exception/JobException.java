package com.sparta.miniproject.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public class JobException extends RuntimeException {
    private final String msg;
    private final HttpStatus status;
}
