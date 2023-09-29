package com.sparta.miniproject.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public class ErrorResponseDto {
        private final String msg;
}
