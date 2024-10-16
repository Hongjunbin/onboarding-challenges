package com.sparta.onboardingchallenges.domain.entity;

import lombok.Getter;

@Getter
public enum Authorities {

    ROLE_USER("ROLE_USER");

    private final String authorityName;

    Authorities(String authorityName) {
        this.authorityName = authorityName;
    }

}
