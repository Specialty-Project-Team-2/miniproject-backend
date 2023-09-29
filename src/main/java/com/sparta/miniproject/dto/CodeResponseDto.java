package com.sparta.miniproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public class CodeResponseDto {
    private final String msg;
}
