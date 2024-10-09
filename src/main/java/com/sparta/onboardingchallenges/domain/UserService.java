package com.sparta.onboardingchallenges.domain;

import com.sparta.onboardingchallenges.domain.dto.SignRequestDto;
import com.sparta.onboardingchallenges.domain.dto.SignResponseDto;
import com.sparta.onboardingchallenges.domain.dto.SignupRequestDto;
import com.sparta.onboardingchallenges.domain.dto.SignupResponseDto;
import com.sparta.onboardingchallenges.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupResponseDto signup(SignupRequestDto requestDto) {

        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new NullPointerException("null");
        }

        User user = userRepository.save(User.builder()
                .username(requestDto.getUsername())
                .encryptedPassword(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .authorities(Authorities.ROLE_USER).build());

        Map<String, String> authorities = new HashMap<>();
        authorities.put("authorityName", user.getAuthorities().name());
        return new SignupResponseDto(user.getUsername(), user.getNickname(), authorities);
    }

    @Transactional
    public SignResponseDto sign(SignRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디의 계정이 존재하지 않습니다.")
        );
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호");
        }

        String accessToken = jwtUtil.createAccessToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);
        user.updateRefreshToken(refreshToken);

        return new SignResponseDto(accessToken);
    }
}