package com.sparta.miniproject.member.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String msg;


    public MemberResponseDto(String msg) {
        this.msg = msg;
    }
}

