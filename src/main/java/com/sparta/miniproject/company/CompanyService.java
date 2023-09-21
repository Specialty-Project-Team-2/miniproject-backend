package com.sparta.miniproject.company;

import com.sparta.miniproject.common.exception.JobException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponseDto readById(Long companyId) {
        return companyRepository.findById(companyId)
                .map(CompanyResponseDto::fromEntity)
                .orElseThrow(() -> JobException.builder()
                        .msg("company.read.not_found")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    public List<CompanyCardResponseDto> readAll(String company) {
        return companyRepository.searchAllBy(company).stream()
                .map(CompanyCardResponseDto::fromEntity)
                .toList();
    }
}
