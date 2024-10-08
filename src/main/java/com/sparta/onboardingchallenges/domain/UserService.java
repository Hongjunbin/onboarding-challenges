package com.sparta.onboardingchallenges.domain;

import com.sparta.onboardingchallenges.domain.dto.SignupRequestDto;
import com.sparta.onboardingchallenges.domain.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        userRepository.save(new User(username, encryptedPassword, nickname));

        return new SignupResponseDto(username, nickname);
    }

}
