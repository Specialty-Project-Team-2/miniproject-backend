package com.sparta.miniproject.cors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("prod")
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "MYSQL_DATABASE_USERNAME=test_account",
        "MYSQL_DATABASE_PASSWORD=q1w2e3r4",
        "MYSQL_URL=localhost:3306",
        "OAUTH2_CLIENT_ID_KAKAO=1234567654123456",
        "FRONTEND_ORIGIN=https://my-web-server-host",
        "KEY_STORE_PASSWORD=q1w2e3r4"
})
class CorsTestInProfileProd {
    @Autowired
    private MockMvc mvc;

    @WithMockUser
    @Test
    @DisplayName("[정상 작동] Preflight 요청 시, 해당 응답이 적절한지 확인")
    void preflight() throws Exception {
        // given
        String clientOrigin = "https://my-web-server-host";
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
}