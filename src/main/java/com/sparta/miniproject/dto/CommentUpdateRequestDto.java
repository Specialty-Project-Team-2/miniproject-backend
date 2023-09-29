package com.sparta.miniproject.dto;

import com.sparta.miniproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommentUpdateRequestDto {
    private String comment;

    public void overwriteTo(Comment request) {
        request.setComment(comment);
    }
}
