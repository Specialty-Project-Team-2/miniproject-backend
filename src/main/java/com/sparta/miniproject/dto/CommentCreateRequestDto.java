package com.sparta.miniproject.dto;

import com.sparta.miniproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommentCreateRequestDto {
    private String comment;

    public Comment toEntity() {
        Comment entity = new Comment();
        entity.setComment(comment);
        return entity;
    }
}
