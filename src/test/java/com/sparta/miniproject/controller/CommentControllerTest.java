package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.request.CommentCreateRequestDto;
import com.sparta.miniproject.dto.request.CommentUpdateRequestDto;
import com.sparta.miniproject.dto.response.CodeResponseDto;
import com.sparta.miniproject.dto.response.CommentResponseDto;
import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.entity.Member;
import com.sparta.miniproject.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.fixture.CommentFixture;
import com.sparta.miniproject.fixture.CompanyFixture;
import com.sparta.miniproject.fixture.MemberFixture;
import com.sparta.miniproject.service.CommentService;
import com.sparta.miniproject.util.EnableGlobalExceptionControllerAdviceTest;
import com.sparta.miniproject.util.WithMockPrincipal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.sparta.miniproject.util.ProxyTestRequest.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(
        controllers = CommentController.class,
        excludeFilters = {
                @Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                JwtAuthorizationFilter.class
                })
        }
)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 생성")
    void create() throws Exception {
        // given
        String urlForTest = "/api/company/1/comment";
        CommentCreateRequestDto request = new CommentCreateRequestDto("mock comment");
        Company company = CompanyFixture.caseWhichRegisteredAtFirst();
        Member member = MemberFixture.caseWhoLoggedIn();

        when(commentService.create(any(), any())).thenReturn(
                CommentResponseDto.fromEntity(CommentFixture.caseWhichMadeFirst(company, member))
        );

        // when & then
        mvc.perform(
                        postAsJson(urlForTest, request)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").hasJsonPath())
                .andExpect(jsonPath("$.nickname").hasJsonPath())
                .andExpect(jsonPath("$.companyId").hasJsonPath());
    }

    @WithMockPrincipal
    @Test
    @DisplayName("[정상 작동] 댓글 수정")
    void update() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);
        CommentUpdateRequestDto request = new CommentUpdateRequestDto("mock comment");
        Company company = CompanyFixture.caseWhichRegisteredAtFirst();
        Member member = MemberFixture.caseWhoLoggedIn();

        when(commentService.update(any(), any(), any())).thenReturn(
                CommentResponseDto.fromEntity(CommentFixture.caseWhichMadeFirst(company, member))
        );

        // when & then
        mvc.perform(
                        patchAsJson(urlForTest, request)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").hasJsonPath())
                .andExpect(jsonPath("$.nickname").hasJsonPath())
                .andExpect(jsonPath("$.companyId").hasJsonPath());
    }

    @WithMockPrincipal
    @Test
    @DisplayName("[정상 작동] 댓글 삭제")
    void deleteMethod() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);

        when(commentService.deleteById(any(), any())).thenReturn(
                CodeResponseDto.fromMsg("테스트를 위한 문장")
        );

        // when & then
        mvc.perform(deleteWithCsrf(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 목록 호출 시, 페이지네이션 작동 여부 확인.")
    void readAllWithPagination() throws Exception {
        // given
        String urlForTest = "/api/comment";

        when(commentService.readAll()).thenReturn(
                CommentFixture.caseList().stream()
                        .map(CommentResponseDto::fromEntity)
                        .toList()
        );

        // when & then
        mvc.perform(get(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").hasJsonPath())
                .andExpect(jsonPath("$[*].comment").hasJsonPath())
                .andExpect(jsonPath("$[*].nickname").hasJsonPath())
                .andExpect(jsonPath("$[*].memberId").hasJsonPath())
                .andExpect(jsonPath("$[*].companyId").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 기업 목록 호출 시, 페이지네이션 작동 여부 확인.")
    void readAllByCompanyIdWithPagination() throws Exception {
        // given
        String urlForTest = "/api/company/1/comment";

        when(commentService.readAllByCompanyId(any(), any())).thenReturn(
                new PageImpl<>(CommentFixture.caseList())
                        .map(CommentResponseDto::fromEntity)
        );

        // when & then
        mvc.perform(
                        get(urlForTest)
                                .param("size", "10")
                                .param("page", "3")
                                .param("sort", "desc,createdAt")
                )
                .andDo(print())
                .andExpect(jsonPath("$.content[*].id").hasJsonPath())
                .andExpect(jsonPath("$.content[*].comment").hasJsonPath())
                .andExpect(jsonPath("$.content[*].nickname").hasJsonPath())
                .andExpect(jsonPath("$.content[*].memberId").hasJsonPath())
                .andExpect(jsonPath("$.content[*].companyId").hasJsonPath());
    }
}