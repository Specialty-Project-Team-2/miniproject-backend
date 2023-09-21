package com.sparta.miniproject.comment;

import com.sparta.miniproject.common.dto.CodeResponseDto;
import com.sparta.miniproject.common.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.tool.EnableGlobalExceptionControllerAdviceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.sparta.miniproject.tool.ProxyTestRequest.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        String urlForTest = "/api/comment";
        CommentCreateRequestDto request = new CommentCreateRequestDto();

        when(commentService.create(any())).thenReturn(
                CommentResponseDto.builder()
                        .comment("mock comment1")
                        .nickname("mock nickname1")
                        .detailid(1L)
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
                .andExpect(jsonPath("$.detailid").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 수정")
    void update() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);
        CommentUpdateRequestDto request = new CommentUpdateRequestDto();

        when(commentService.update(any(), any())).thenReturn(
                CommentResponseDto.builder()
                        .comment("mock comment1")
                        .nickname("mock nickname1")
                        .detailid(1L)
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
                .andExpect(jsonPath("$.detailid").hasJsonPath());
    }

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] 댓글 삭제")
    void deleteMethod() throws Exception {
        // given
        Long id = 1L;
        String urlForTest = String.format("/api/comment/%s", id);

        when(commentService.deleteById(any())).thenReturn(
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
}