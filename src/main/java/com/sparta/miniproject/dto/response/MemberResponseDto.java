package com.sparta.miniproject.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class MemberResponseDto {
    private final String msg;

    public static MemberResponseDto fromMsg(String msg) {
        return new MemberResponseDto(msg);
    }
}