package com.sparta.miniproject.dto.response;

import com.sparta.miniproject.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final String nickname;
    private final Long memberId;
    private final Long companyId;

    public static CommentResponseDto fromEntity(Comment entity) {
        return new CommentResponseDto(
                entity.getId(),
                entity.getComment(),
                entity.getMember().getNickname(),
                entity.getMember().getId(),
                entity.getCompany().getId()
        );
    }
}
