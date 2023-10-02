package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sparta.miniproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class CommentUpdateRequestDto {
    private final String comment;

    @JsonCreator
    public CommentUpdateRequestDto(
            String comment
    ) {
        this.comment = comment;
    }

    public void overwriteTo(Comment entity) {
        entity.setComment(comment);
    }
}
