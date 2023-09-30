package com.sparta.miniproject.dto.response;

import com.sparta.miniproject.entity.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public final class CompanyCardResponseDto {
    private final Long id;
    private final String companyName;
    private final String location;
    private final String logoUrl;

    public static CompanyCardResponseDto fromEntity(Company entity) {
        return new CompanyCardResponseDto(
                entity.getId(),
                entity.getCompanyName(),
                entity.getLocation(),
                entity.getLogoUrl()
        );
    }
}
