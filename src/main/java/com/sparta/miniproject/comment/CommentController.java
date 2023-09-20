package com.sparta.miniproject.comment;

import com.sparta.miniproject.common.dto.CodeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public CommentResponseDto create(@RequestBody CommentCreateRequestDto request) {
        return commentService.create(request);
    }

    @GetMapping("/api/company/{companyId}/comment")
    public List<CommentResponseDto> readAll(@PathVariable Long companyId) {
        return commentService.readAll(companyId);
    }

    @PatchMapping("/api/comment/{commentId}")
    public CommentResponseDto update(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto request) {
        return commentService.update(commentId, request);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public CodeResponseDto delete(@PathVariable Long commentId) {
        return commentService.deleteById(commentId);
    }
}