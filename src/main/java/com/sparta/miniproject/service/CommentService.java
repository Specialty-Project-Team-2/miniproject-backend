package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.CodeResponseDto;
import com.sparta.miniproject.dto.CommentCreateRequestDto;
import com.sparta.miniproject.dto.CommentResponseDto;
import com.sparta.miniproject.dto.CommentUpdateRequestDto;
import com.sparta.miniproject.entity.Comment;
import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.entity.Member;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.exception.MessageSourceUtil;
import com.sparta.miniproject.repository.CommentRepository;
import com.sparta.miniproject.repository.CompanyRepository;
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
    public CommentResponseDto update(Long commentId, CommentUpdateRequestDto request, Member principal) {
        Comment entity = findById(commentId);

        validateOwnerShipOfComment(principal, entity.getMember());

        request.overwriteTo(entity);
        return CommentResponseDto.fromEntity(entity);
    }

    @Transactional
    public CodeResponseDto deleteById(Long commentId, Member principal) {
        Comment entity = findById(commentId);

        validateOwnerShipOfComment(principal, entity.getMember());

        commentRepository.delete(entity);

        String message = source.interpretErrorMessage("comment.delete.success");
        return CodeResponseDto.fromMsg(message);
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> JobException.from(HttpStatus.BAD_REQUEST, "comment.read.not_found"));
    }

    private Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> JobException.from(HttpStatus.BAD_REQUEST, "company.read.not_found"));
    }

    private static void validateOwnerShipOfComment(Member memberLoggedIn, Member memberWhoOwnedThis) {
        if(memberLoggedIn == null) {
            throw JobException.from(HttpStatus.UNAUTHORIZED, "authentication.empty");
        }

        if(memberLoggedIn.getId() != memberWhoOwnedThis.getId()) {
            throw JobException.from(HttpStatus.FORBIDDEN, "comment.access.denied");
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
