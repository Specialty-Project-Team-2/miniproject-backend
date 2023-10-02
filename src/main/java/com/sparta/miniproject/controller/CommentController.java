package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.request.CommentCreateRequestDto;
import com.sparta.miniproject.dto.request.CommentUpdateRequestDto;
import com.sparta.miniproject.dto.response.CodeResponseDto;
import com.sparta.miniproject.dto.response.CommentResponseDto;
import com.sparta.miniproject.security.UserDetailsImpl;
import com.sparta.miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/company/{companyId}/comment")
    public CommentResponseDto create(
            @PathVariable Long companyId,
            @RequestBody CommentCreateRequestDto request
    ) {
        return commentService.create(companyId, request);
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
    public CommentResponseDto update(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto request,
            @AuthenticationPrincipal UserDetailsImpl principal
    ) {
        return commentService.update(commentId, request, principal.getMember());
    }

    @DeleteMapping("/api/comment/{commentId}")
    public CodeResponseDto delete(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl principal
            ) {
        return commentService.deleteById(commentId, principal.getMember());
    }
}