package com.sparta.miniproject.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class LoginResponseDto {
    private final String msg;
    private final String token;
}