package com.sparta.onboardingchallenges.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String msg;
    private String path;
}
