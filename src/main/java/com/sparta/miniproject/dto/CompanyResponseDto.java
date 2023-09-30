package com.sparta.miniproject.dto;

import com.sparta.miniproject.entity.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public final class CompanyResponseDto {
    private final Long id;
    private final String companyName;
    private final String location;
    private final String sales;
    private final String logoUrl;

    public static CompanyResponseDto fromEntity(Company entity) {
        return new CompanyResponseDto(
                entity.getId(),
                entity.getCompanyName(),
                entity.getLocation(),
                entity.getSales(),
                entity.getLogoUrl()
        );
    }
}
