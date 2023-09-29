package com.sparta.miniproject.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@Component
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionControllerAdvice {
    private final MessageSourceUtil source;

    @ExceptionHandler(JobException.class)
    public ErrorResponseDto JobExceptionHandler(HttpServletResponse response, JobException ex) {
        response.setStatus(ex.getStatus().value());

        String messageToClient = source.interpretErrorMessage(ex.getMsg());
        log.warn(messageToClient);
        return ErrorResponseDto.builder()
                .msg(messageToClient)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto validationHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        String messageToClient = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn(messageToClient);
        return ErrorResponseDto.builder()
                .msg(messageToClient)
                .build();
    }

    @ExceptionHandler(Throwable.class)
    public ErrorResponseDto UnexpectedExceptionHandler(HttpServletResponse response, Throwable ex) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        String messageToClient = source.interpretErrorMessage("unexpected.error");
        Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .forEach(log::warn);
        return ErrorResponseDto.builder()
                .msg(messageToClient)
                .build();
    }
}
