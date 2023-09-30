package com.sparta.miniproject.comment;

import com.sparta.miniproject.controller.CommentController;
import com.sparta.miniproject.dto.CodeResponseDto;
import com.sparta.miniproject.dto.CommentCreateRequestDto;
import com.sparta.miniproject.dto.CommentResponseDto;
import com.sparta.miniproject.dto.CommentUpdateRequestDto;
import com.sparta.miniproject.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.service.CommentService;
import com.sparta.miniproject.util.EnableGlobalExceptionControllerAdviceTest;
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
        CommentCreateRequestDto request = new CommentCreateRequestDto();

        when(commentService.create(any(), any())).thenReturn(
                CommentResponseDto.builder()
                        .comment("mock comment1")
                        .nickname("mock nickname1")
                        .companyId(1L)
                        .build()
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

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 수정")
    void update() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);
        CommentUpdateRequestDto request = new CommentUpdateRequestDto();

        when(commentService.update(any(), any(), any())).thenReturn(
                CommentResponseDto.builder()
                        .comment("mock comment1")
                        .nickname("mock nickname1")
                        .companyId(1L)
                        .build()
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

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 삭제")
    void deleteMethod() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);

        when(commentService.deleteById(any(), any())).thenReturn(
                CodeResponseDto.builder()
                        .msg("테스트를 위한 문장")
                        .build()
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
                CommentFixture.caseList1().stream()
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
                new PageImpl<>(CommentFixture.caseList1())
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