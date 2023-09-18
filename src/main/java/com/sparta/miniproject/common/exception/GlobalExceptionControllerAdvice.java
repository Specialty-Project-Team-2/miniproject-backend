package com.sparta.miniproject.common.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionControllerAdvice {
    private final MessageSourceUtil source;

    @ExceptionHandler(JobException.class)
    public ErrorResponseDto JobExceptionHandler(HttpServletResponse response, JobException ex) {
        response.setStatus(ex.getStatus().value());

        String messageToClient = source.interpretErrorMessage(ex.getMsg());
        return ErrorResponseDto.builder()
                .msg(messageToClient)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto validationHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ErrorResponseDto.builder()
                .msg(msg)
                .build();
    }

    @ExceptionHandler(Throwable.class)
    public ErrorResponseDto UnexpectedExceptionHandler(HttpServletResponse response, Throwable ex) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        String messageToClient = source.interpretErrorMessage("unexpected.error");
        return ErrorResponseDto.builder()
                .msg(messageToClient)
                .build();
    }
}
