package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.CommentCreateRequestDto;
import com.sparta.miniproject.dto.CommentResponseDto;
import com.sparta.miniproject.dto.CommentUpdateRequestDto;
import com.sparta.miniproject.dto.CodeResponseDto;
import com.sparta.miniproject.entity.Comment;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.exception.MessageSourceUtil;
import com.sparta.miniproject.repository.CommentRepository;
import com.sparta.miniproject.utils.SecurityUtil;
import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.repository.CompanyRepository;
import com.sparta.miniproject.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CompanyRepository companyRepository;
    private final MessageSourceUtil source;

    @Transactional
    public CommentResponseDto create(Long companyId, CommentCreateRequestDto request) {
        Comment entity = request.toEntity();

        // 회사 엔티티 주입.
        Company companyEntity = findCompanyById(companyId);
        entity.setCompany(companyEntity);

        Comment saved = commentRepository.save(entity);
        return CommentResponseDto.fromEntity(saved);
    }

    @Transactional
    public CommentResponseDto update(Long commentId, CommentUpdateRequestDto request) {
        Comment entity = findById(commentId);

        isThisYours(entity.getMember());

        request.overwriteTo(entity);
        return CommentResponseDto.fromEntity(entity);
    }

    @Transactional
    public CodeResponseDto deleteById(Long commentId) {
        Comment entity = findById(commentId);

        isThisYours(entity.getMember());

        commentRepository.delete(entity);

        String message = source.interpretErrorMessage("comment.delete.success");
        return CodeResponseDto.builder()
                .msg(message)
                .build();
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> JobException.builder()
                        .msg("comment.read.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    private Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> JobException.builder()
                        .msg("company.read.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    private static void isThisYours(Member memberWhoOwnedThis) {
        Member memberLoggedIn = SecurityUtil.getMemberLoggedIn()
                .orElseThrow(() ->
                        JobException.builder()
                                .msg("authentication.empty")
                                .status(HttpStatus.UNAUTHORIZED)
                                .build()
                        );

        if(!(memberLoggedIn.getId() == memberWhoOwnedThis.getId())) {
            throw JobException.builder()
                    .msg("comment.access.denied")
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
    }

    public List<CommentResponseDto> readAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    public Page<CommentResponseDto> readAllByCompanyId(Long companyId, Pageable pageable) {
        return commentRepository.findByCompany_Id(companyId, pageable)
                .map(CommentResponseDto::fromEntity);
    }
}
