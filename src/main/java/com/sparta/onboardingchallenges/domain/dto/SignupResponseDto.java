package com.sparta.onboardingchallenges.domain.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private final String username;
    private final String nickname;

    public SignupResponseDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
