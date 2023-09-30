package com.sparta.miniproject.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.miniproject.dto.response.ErrorResponseDto;
import com.sparta.miniproject.utils.MessageSourceUtil;
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

        ErrorResponseDto responseDto = ErrorResponseDto.fromMsg(message);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try (PrintWriter writer = response.getWriter()) {
            String jsonData = objectMapper.writeValueAsString(responseDto);
            writer.print(jsonData);
        }
    }
}
