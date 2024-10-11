package com.sparta.onboardingchallenges.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> defaultException(HttpServletRequest request, Exception e) {
        log.error("defaultException : {}", e.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .msg(ErrorCode.FAIL.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(HttpServletRequest request, CustomException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .msg(e.getErrorCode().getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, e.getErrorCode().getStatus());
    }

}
