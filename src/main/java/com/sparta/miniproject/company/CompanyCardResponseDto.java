package com.sparta.miniproject.company;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public final class CompanyCardResponseDto {
    private final String companyName;
    private final String location;

    public static CompanyCardResponseDto fromEntity(Company entity) {
        return CompanyCardResponseDto.builder()
                .companyName(entity.getCompanyName())
                .location(entity.getLocation())
                .build();
    }
}
