package com.sparta.miniproject.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    private final MessageSourceUtil source;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        String message = source.interpretErrorMessage("access.denied");

        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .msg(message)
                .build();

        response.setStatus(HttpStatus.FORBIDDEN.value());

        try (PrintWriter writer = response.getWriter()) {
            String jsonData = objectMapper.writeValueAsString(responseDto);
            writer.print(jsonData);
        }
    }
}
