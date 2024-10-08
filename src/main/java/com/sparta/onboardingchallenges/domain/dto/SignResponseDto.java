package com.sparta.onboardingchallenges.domain.dto;

import lombok.Getter;

@Getter
public class SignResponseDto {
    private final String token;

    public SignResponseDto(String accessToken) {
        this.token = accessToken;
    }
}
