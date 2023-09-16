package com.sparta.miniproject.company;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder(toBuilder = true) @RequiredArgsConstructor
public final class CompanyResponseDto {
    private final String companyName;
    private final String location;
    private final String sales;

    public static CompanyResponseDto fromEntity(Company entity) {
        return CompanyResponseDto.builder()
                .companyName(entity.getCompanyName())
                .location(entity.getLocation())
                .sales(entity.getSales())
                .build();
    }
}
