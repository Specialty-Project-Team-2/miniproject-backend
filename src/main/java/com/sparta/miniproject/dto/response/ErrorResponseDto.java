package com.sparta.miniproject.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class ErrorResponseDto {
        private final String msg;

        public static ErrorResponseDto fromMsg(String msg) {
                return new ErrorResponseDto(msg);
        }
}
