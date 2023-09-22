package com.sparta.miniproject.comment;

import com.sparta.miniproject.common.dto.CodeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/api/comment")
    public List<CommentResponseDto> readAll() {
        return commentService.readAll();
    }

    @GetMapping("/api/company/{companyId}/comment")
    public Page<CommentResponseDto> readAllByCompanyId(
            @PathVariable Long companyId,
            @PageableDefault Pageable pageable
    ) {
        return commentService.readAllByCompanyId(companyId, pageable);
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