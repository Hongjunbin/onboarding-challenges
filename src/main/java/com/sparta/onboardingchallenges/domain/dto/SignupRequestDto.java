package com.sparta.onboardingchallenges.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수로 입력되어야 합니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^*+=\\-])(?=.*[0-9]).{8,15}$",
            message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 이루어져 있어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수로 입력되어야 합니다.")
    private String nickname;
}
