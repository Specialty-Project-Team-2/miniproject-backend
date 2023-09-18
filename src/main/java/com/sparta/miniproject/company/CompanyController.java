package com.sparta.miniproject.company;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/detail/{companyId}")
    public CompanyResponseDto readById(@PathVariable Long companyId) {
        return companyService.readById(companyId);
    }

    @GetMapping("/")
    public List<CompanyCardResponseDto> readAll() {
        return companyService.readAll();
    }
}
