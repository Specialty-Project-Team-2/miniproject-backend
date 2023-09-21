package com.sparta.miniproject.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final String nickname;
    private final Long memberId;
    private final Long detailid;

    public static CommentResponseDto fromEntity(Comment entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .nickname(entity.getMember().getNickname())
                .memberId(entity.getMember().getId())
                .detailid(entity.getCompany().getId())
                .build();
    }
}
