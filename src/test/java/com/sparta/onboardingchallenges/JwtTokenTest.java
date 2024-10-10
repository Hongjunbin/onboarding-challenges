package com.sparta.onboardingchallenges;

import com.sparta.onboardingchallenges.security.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTokenTest {

    private JwtUtil jwtUtil;

    private static final String TEST_SECRET_KEY = "7KCc67CcIOuCmCDsooAg7IK066Ck7KSYIOuCmOuKlCDsnbTsoJwg7J6Q67CUIOyigCDqt7jrp4ztlZjqs6Ag7KKAIOyekOqzoCDsi7bri6Qu";

    @BeforeEach
    public void setUp() {
        Keys.secretKeyFor(SignatureAlgorithm.HS256);
        jwtUtil = new JwtUtil(TEST_SECRET_KEY);
    }

    @Test // 토큰 생성 테스트
    @DisplayName("createAccessTokenTest")
    void testCreateAccessToken() {
        // given
        String username = "tester";
        // when
        String accessToken = jwtUtil.createAccessToken(username);
        // then
        assertNotNull(accessToken);
        assertTrue(accessToken.startsWith("Bearer"));
    }

    @Test // 토큰 검증 테스트
    @DisplayName("validateTokenTest")
    void testValidateToken() {
        // given
        String username = "tester";
        // when
        String accessToken = jwtUtil.createAccessToken(username);
        String accessTokenValue = jwtUtil.getJwtFromHeader(accessToken);
        boolean isAccessToken = jwtUtil.validateToken(accessTokenValue);
        // then
        assertTrue(isAccessToken);
    }

    @Test // 토큰 검증 실패 테스트
    @DisplayName("InvalidAccessToken")
    void testInvalidAccessToken() {
        // given
        String username = "tester";
        // when
        String accessToken = jwtUtil.createAccessToken(username);
        String accessTokenValue = jwtUtil.getJwtFromHeader(accessToken);
        String invalidAccessToken = accessTokenValue.substring(5);
        assertThrows(Exception.class, () -> {
            jwtUtil.validateToken(invalidAccessToken);
        });
    }

}