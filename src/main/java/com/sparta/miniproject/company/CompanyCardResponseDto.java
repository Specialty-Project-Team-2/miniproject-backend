package com.sparta.miniproject.company;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public final class CompanyCardResponseDto {
    private final Long id;
    private final String companyName;
    private final String location;
    private final String logoUrl;

    public static CompanyCardResponseDto fromEntity(Company entity) {
        return CompanyCardResponseDto.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .location(entity.getLocation())
                .logoUrl(entity.getLogoUrl())
                .build();
    }
}
