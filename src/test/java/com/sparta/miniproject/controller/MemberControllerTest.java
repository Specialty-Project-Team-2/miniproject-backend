package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.request.MemberRequestDto;
import com.sparta.miniproject.dto.response.MypageResponsDto;
import com.sparta.miniproject.utils.MessageSourceUtil;
import com.sparta.miniproject.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.fixture.MemberFixture;
import com.sparta.miniproject.service.MemberService;
import com.sparta.miniproject.util.EnableGlobalExceptionControllerAdviceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.sparta.miniproject.util.ArgumentMatchersUtil.except;
import static com.sparta.miniproject.util.ProxyTestRequest.putAsJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(
        controllers = MemberController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                JwtAuthorizationFilter.class
                        })
        }
)
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;
    @MockBean
    private MessageSourceUtil source;

    @InjectMocks
    private MemberController memberController;

    private static final String VALID_NICKNAME = "iksadnorth";

    <T> void dtoValidationTest(
            String urlForTest,
            T request,
            String messageSource
    ) throws Exception {
        String errorMessage = "원하는 에러 메시지.";
        String errorMessageIncorrect = "원치 않는 에러 메시지.";

        when(source.interpretErrorMessage(eq(messageSource)))
                .thenReturn(errorMessage);
        when(source.interpretErrorMessage(except(messageSource)))
                .thenReturn(errorMessageIncorrect);

        // when & then
        mvc.perform(
                        putAsJson(urlForTest, request)
                )
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.msg").value(errorMessage));
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 비밀번호를 이상한 형식으로 넘길 때, 오류 메시지 발생.")
    void updateWhenOccurWrongEmailForm() throws Exception {
        // given
        String urlForTest = "/api/member/me";
        MemberRequestDto request = new MemberRequestDto(
                VALID_NICKNAME, "wrong password form"
        );
        String messageSource = "signup.password.not_password_form";

        when(memberService.mypageUpdate(any(), any())).thenReturn(
                MypageResponsDto.fromEntity(MemberFixture.caseWhoLoggedIn())
        );

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }
}