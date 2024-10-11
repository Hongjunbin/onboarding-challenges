package com.sparta.onboardingchallenges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OnboardingChallengesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingChallengesApplication.class, args);
    }

}
