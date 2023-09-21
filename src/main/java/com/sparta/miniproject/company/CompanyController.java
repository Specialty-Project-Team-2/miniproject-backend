package com.sparta.miniproject.company;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/detail/{companyId}")
    public CompanyResponseDto readById(@PathVariable Long companyId) {
        return companyService.readById(companyId);
    }

    @GetMapping("/")
    public Page<CompanyCardResponseDto> readAll(
            @PageableDefault Pageable pageable
    ) {
        return companyService.readAll(pageable);
    }
}
