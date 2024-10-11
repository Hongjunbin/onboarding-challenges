package com.sparta.onboardingchallenges.security;

import com.sparta.onboardingchallenges.common.exception.CustomException;
import com.sparta.onboardingchallenges.common.exception.ErrorCode;
import com.sparta.onboardingchallenges.domain.entity.User;
import com.sparta.onboardingchallenges.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return new UserDetailsImpl(user);
    }

}
