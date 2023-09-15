package com.sparta.miniproject.comment;

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
