package com.sparta.miniproject.comment;

import com.sparta.miniproject.common.dto.CodeResponseDto;
import com.sparta.miniproject.common.exception.JobException;
import com.sparta.miniproject.common.exception.MessageSourceUtil;
import com.sparta.miniproject.common.util.SecurityUtil;
import com.sparta.miniproject.company.Company;
import com.sparta.miniproject.company.CompanyRepository;
import com.sparta.miniproject.member.entity.Member;
import lombok.RequiredArgsConstructor;
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
    public CommentResponseDto create(CommentCreateRequestDto request) {
        Comment entity = request.toEntity();

        // 회사 엔티티 주입.
        Long detailId = request.getDetailid();
        Company companyEntity = findCompanyById(detailId);
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

    public List<CommentResponseDto> readAllByCompanyId(Long companyId) {
        return commentRepository.findByCompany_Id(companyId).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }
}
