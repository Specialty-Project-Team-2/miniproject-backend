package com.sparta.miniproject.company;

import com.sparta.miniproject.common.exception.JobException;
import com.sparta.miniproject.tool.EnableGlobalExceptionControllerAdviceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Test
    @DisplayName("[정상 작동] 기업 상세 정보 호출")
    void readById() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/detail/%s", id);

        when(companyService.readById(any())).thenReturn(
                CompanyResponseDto.fromEntity(CompanyFixture.case1())
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").hasJsonPath())
                .andExpect(jsonPath("$.location").hasJsonPath())
                .andExpect(jsonPath("$.sales").hasJsonPath());
    }

    @Test
    @DisplayName("[비정상 작동] 존재하지 않는 Id 호출")
    void readById_withIdNotExisted() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/detail/%s", id);
        HttpStatus status = HttpStatus.CONFLICT;

        when(companyService.readById(any())).thenThrow(
                JobException.builder()
                        .msg("sentence.for.test")
                        .status(status)
                        .build()
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().is(status.value()))
                .andExpect(jsonPath("$.msg").hasJsonPath());
    }

    @Test
    @DisplayName("[정상 작동] 기업 목록 호출")
    void readAll() throws Exception {
        // given
        String urlForTest = "/";

        when(companyService.readAll()).thenReturn(
                CompanyFixture.caseList1().stream()
                        .map(CompanyCardResponseDto::fromEntity)
                        .toList()
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].companyName").hasJsonPath())
                .andExpect(jsonPath("$[*].location").hasJsonPath());
    }
}