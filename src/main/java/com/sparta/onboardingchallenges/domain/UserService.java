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

        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new NullPointerException("null");
        }

        User user = userRepository.save(User.builder()
                        .username(requestDto.getUsername())
                        .encryptedPassword(passwordEncoder.encode(requestDto.getPassword()))
                        .nickname(requestDto.getNickname())
                        .authorities(Authorities.ROLE_USER).build());

        return new SignupResponseDto(user.getUsername(), user.getNickname());
    }

}
