package com.sparta.miniproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class CodeResponseDto {
    private final String msg;

    public static CodeResponseDto fromMsg(String msg) {
        return new CodeResponseDto(msg);
    }
}
