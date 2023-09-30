package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.CompanyCardResponseDto;
import com.sparta.miniproject.dto.CompanyResponseDto;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponseDto readById(Long companyId) {
        return companyRepository.findById(companyId)
                .map(CompanyResponseDto::fromEntity)
                .orElseThrow(() -> JobException.from(HttpStatus.BAD_REQUEST, "company.read.not_found"));
    }

    public Page<CompanyCardResponseDto> readAll(String company, Pageable pageable) {
        return companyRepository.searchAllBy(company, pageable)
                .map(CompanyCardResponseDto::fromEntity);
    }
}
