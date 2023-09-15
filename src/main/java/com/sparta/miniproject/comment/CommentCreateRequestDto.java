package com.sparta.miniproject.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommentCreateRequestDto {
    private String comment;
    private Long detailid;

    public Comment toEntity() {
        Comment entity = new Comment();
        entity.setComment(comment);
        return entity;
    }
}
