package com.sparta.onboardingchallenges.domain;

import com.sparta.onboardingchallenges.domain.dto.SignupRequestDto;
import com.sparta.onboardingchallenges.domain.dto.SignupResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 기능")
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto responseDto = userService.signup(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
