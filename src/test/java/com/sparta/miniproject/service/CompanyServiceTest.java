package com.sparta.miniproject.service;

import com.sparta.miniproject.dto.response.CompanyResponseDto;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.fixture.CompanyFixture;
import com.sparta.miniproject.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompanyServiceTest {
    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final CompanyService companyService = new CompanyService(companyRepository);

    @Test
    @DisplayName("[정상 작동] 요구하는 게시글 ID가 존재하면 해당 게시글 출력")
    void readById() {
        // given
        Long companyId = 1L;

        when(companyRepository.findById(any())).thenReturn(
                Optional.of(CompanyFixture.caseWhichRegisteredAtFirst())
        );

        // when
        CompanyResponseDto response = companyService.readById(companyId);

        // then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("[비정상 작동] 요구하는 게시글 ID가 존재하지 않으면 예외 출력")
    void readByIdOccurWhenNonExistedCompany() {
        // given
        Long companyId = 1L;

        when(companyRepository.findById(any())).thenReturn(
                Optional.empty()
        );

        // when
        Executable result = () -> companyService.readById(companyId);

        // then
        assertThrows(JobException.class, result);
    }
}