package com.sparta.miniproject.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.miniproject.common.dto.CodeResponseDto;
import com.sparta.miniproject.tool.EnableGlobalExceptionControllerAdviceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private ObjectMapper objectMapper = new ObjectMapper();

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
                post(urlForTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").hasJsonPath())
                .andExpect(jsonPath("$.nickname").hasJsonPath())
                .andExpect(jsonPath("$.detailid").hasJsonPath());
    }

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
                patch(urlForTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").hasJsonPath())
                .andExpect(jsonPath("$.nickname").hasJsonPath())
                .andExpect(jsonPath("$.detailid").hasJsonPath());
    }

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
        mvc.perform(delete(urlForTest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").hasJsonPath());
    }
}