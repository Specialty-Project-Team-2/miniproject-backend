package com.sparta.miniproject.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String msg;

    private MemberResponseDto(String msg) {
        this.msg = msg;
    }

    public static MemberResponseDto fromMsg(String msg) {
        return new MemberResponseDto(msg);
    }
}