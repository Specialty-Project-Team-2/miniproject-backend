package com.sparta.miniproject.controller;

import com.sparta.miniproject.dto.request.SignupRequestDto;
import com.sparta.miniproject.utils.MessageSourceUtil;
import com.sparta.miniproject.filter.JwtAuthorizationFilter;
import com.sparta.miniproject.service.KakaoService;
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
import static com.sparta.miniproject.util.ProxyTestRequest.postAsJson;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableGlobalExceptionControllerAdviceTest
@WebMvcTest(
        controllers = AuthController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                JwtAuthorizationFilter.class
                        })
        }
)
class AuthControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private KakaoService kakaoService;
    @MockBean
    private MemberService memberService;
    @MockBean
    private MessageSourceUtil source;

    @InjectMocks
    private AuthController authController;

    private static final String VALID_EMAIL = "iksadnorth@gmail.com";
    private static final String VALID_PASSWORD = "q1w2e3r4";
    private static final String VALID_NICKNAME = "적절한 닉네임";

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
                        postAsJson(urlForTest, request)
                )
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.msg").value(errorMessage));
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 이메일을 빈값으로 넘길 때, 오류 메시지 발생.")
    void createWhenOccurEmptyEmail() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                "", VALID_PASSWORD, VALID_NICKNAME
        );
        String messageSource = "signup.email.empty_input";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 이메일을 이상한 형식으로 넘길 때, 오류 메시지 발생.")
    void createWhenOccurWrongEmailForm() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                "wrong email form", VALID_PASSWORD, VALID_NICKNAME
        );
        String messageSource = "signup.email.not_email_form";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 비밀번호를 빈값으로 넘길 때, 오류 메시지 발생.")
    void createWhenOccurNullPassword() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                VALID_EMAIL, "", VALID_NICKNAME
        );
        String messageSource = "signup.password.inapposite_size";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 비밀번호를 적절치 못한 길이의 비번을 넘길 때, 오류 메시지 발생.")
    void createWhenOccurInappositeSize() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                VALID_EMAIL, "aaa", VALID_NICKNAME
        );
        String messageSource = "signup.password.inapposite_size";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 비밀번호를 적절치 못한 형식의 비번을 넘길 때, 오류 메시지 발생.")
    void createWhenOccurNotPasswordForm() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                VALID_EMAIL, "적절하지 못한 비번", VALID_NICKNAME
        );
        String messageSource = "signup.password.not_password_form";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 닉네임으로 빈값을 넘길 때, 오류 메시지 발생.")
    void createWhenOccurNullNickname() throws Exception {
        // given
        String urlForTest = "/api/signup";
        SignupRequestDto request = new SignupRequestDto(
                VALID_EMAIL, VALID_PASSWORD, ""
        );
        String messageSource = "signup.nickname.empty_input";

        // when & then
        dtoValidationTest(urlForTest, request, messageSource);
    }
}