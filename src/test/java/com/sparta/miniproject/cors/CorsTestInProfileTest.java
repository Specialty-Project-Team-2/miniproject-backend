package com.sparta.miniproject.cors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CorsTestInProfileTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("[정상 작동] Preflight 요청 시, 해당 응답이 적절한지 확인")
    void preflight() throws Exception {
        // given
        String clientOrigin = "http://localhost:3000";
        String method = "POST";
        String host = "/api/signup";

        // when & then
        mvc.perform(
                options(host)
                        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, method)
                        .header(HttpHeaders.ORIGIN, clientOrigin)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, clientOrigin));
    }

    @WithMockUser
    @Test
    @DisplayName("[비정상 작동] 지정하지 않은 origin에서 Preflight 요청 시, 해당 응답이 부적절한지 확인")
    void preflightOccurErrorWhenRequestFromUnregisteredOrigin() throws Exception {
        // given
        String clientOrigin = "https://other-web-server-host";
        String method = "POST";
        String host = "/api/signup";

        // when & then
        mvc.perform(
                        options(host)
                                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, method)
                                .header(HttpHeaders.ORIGIN, clientOrigin)
                )
                .andDo(print())
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}