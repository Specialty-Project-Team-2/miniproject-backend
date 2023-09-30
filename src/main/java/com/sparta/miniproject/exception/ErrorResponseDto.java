package com.sparta.miniproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class ErrorResponseDto {
        private final String msg;

        public static ErrorResponseDto fromMsg(String msg) {
                return new ErrorResponseDto(msg);
        }
}
