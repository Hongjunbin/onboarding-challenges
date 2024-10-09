package com.sparta.onboardingchallenges.domain.dto;

import lombok.Getter;
import java.util.Map;

@Getter
public class SignupResponseDto {
    private final String username;
    private final String nickname;
    private final Map<String, String> authorities;

    public SignupResponseDto(String username, String nickname, Map<String, String> authorities) {
        this.username = username;
        this.nickname = nickname;
        this.authorities = authorities;
    }
}