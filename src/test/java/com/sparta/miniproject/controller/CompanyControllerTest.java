package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.response.CompanyCardResponseDto;
import com.sparta.miniproject.dto.response.CompanyResponseDto;
import com.sparta.miniproject.exception.JobException;
import com.sparta.miniproject.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.fixture.CompanyFixture;
import com.sparta.miniproject.service.CompanyService;
import com.sparta.miniproject.util.EnableGlobalExceptionControllerAdviceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(
        controllers = CompanyController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                JwtAuthorizationFilter.class
                        })
        }
)
class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 기업 상세 정보 호출")
    void readById() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/company/%s", id);

        when(companyService.readById(any())).thenReturn(
                CompanyResponseDto.fromEntity(CompanyFixture.caseWhichRegisteredAtFirst())
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").hasJsonPath())
                .andExpect(jsonPath("$.location").hasJsonPath())
                .andExpect(jsonPath("$.logoUrl").hasJsonPath())
                .andExpect(jsonPath("$.sales").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 존재하지 않는 Id 호출")
    void readById_withIdNotExisted() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/company/%s", id);
        HttpStatus status = HttpStatus.CONFLICT;

        when(companyService.readById(any())).thenThrow(
                JobException.from(status, "sentence.for.test")
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().is(status.value()))
                .andExpect(jsonPath("$.msg").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 기업 목록 호출")
    void readAll() throws Exception {
        // given
        String urlForTest = "/api/company";

        when(companyService.readAll(any(), any())).thenReturn(
                new PageImpl<>(CompanyFixture.caseList1())
                        .map(CompanyCardResponseDto::fromEntity)
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].companyName").hasJsonPath())
                .andExpect(jsonPath("$.content[*].logoUrl").hasJsonPath())
                .andExpect(jsonPath("$.content[*].location").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 기업 목록 호출 시, 페이지네이션 작동 여부 확인.")
    void readAllWithPagination() throws Exception {
        // given
        String urlForTest = "/api/company";

        when(companyService.readAll(any(), any())).thenReturn(
                new PageImpl<>(CompanyFixture.caseList1())
                        .map(CompanyCardResponseDto::fromEntity)
        );

        // when & then
        mvc.perform(
                get(urlForTest)
                        .param("size", "10")
                        .param("page", "3")
                        .param("sort", "desc,createdAt")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].companyName").hasJsonPath())
                .andExpect(jsonPath("$.content[*].logoUrl").hasJsonPath())
                .andExpect(jsonPath("$.content[*].location").hasJsonPath());
    }
}