package com.sparta.miniproject.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    private final MessageSourceUtil source;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        String message = source.interpretErrorMessage("authentication.empty");

        ErrorResponseDto responseDto = ErrorResponseDto.fromMsg(message);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        try (PrintWriter writer = response.getWriter()) {
            String jsonData = objectMapper.writeValueAsString(responseDto);
            writer.print(jsonData);
        }
    }
}
