package com.sparta.miniproject.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String msg;
    private String token;


    public LoginResponseDto(String msg, String token) {
        this.msg = msg;
        this.token = token;
    }
}