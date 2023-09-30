package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.response.CompanyCardResponseDto;
import com.sparta.miniproject.dto.response.CompanyResponseDto;
import com.sparta.miniproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/api/company/{companyId}")
    public CompanyResponseDto readById(@PathVariable Long companyId) {
        return companyService.readById(companyId);
    }

    @GetMapping("/api/company")
    public Page<CompanyCardResponseDto> readAll(
            @RequestParam(name = "name", required = false) String keyword,
            @PageableDefault Pageable pageable
    ) {
        return companyService.readAll(keyword, pageable);
    }
}
