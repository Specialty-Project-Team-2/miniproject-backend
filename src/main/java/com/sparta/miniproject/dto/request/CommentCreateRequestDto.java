package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sparta.miniproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class CommentCreateRequestDto {
    private final String comment;

    @JsonCreator
    public CommentCreateRequestDto(
            String comment
    ) {
        this.comment = comment;
    }

    public Comment toEntity() {
        Comment entity = new Comment();
        entity.setComment(comment);
        return entity;
    }
}
