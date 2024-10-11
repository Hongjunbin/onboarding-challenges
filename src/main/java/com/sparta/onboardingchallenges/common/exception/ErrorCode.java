package com.sparta.onboardingchallenges.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저를 찾을 수 없습니다."),
    USER_NOT_UNIQUE(HttpStatus.BAD_REQUEST, "이미 회원 가입한 상태입니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호가 틀렸습니다.");

    private final HttpStatus status;
    private final String message;

}