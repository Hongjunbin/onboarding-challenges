package com.sparta.onboardingchallenges.security;

import com.sparta.onboardingchallenges.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    public static final String ACCESS_TOKEN_HEADER = "AccessToken";
    public static final String REFRESH_TOKEN_HEADER = "RefreshToken";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final long ACCESS_TOKEN_TIME = 1209600000; // 기존 1800000
    public static final long REFRESH_TOKEN_TIME = 1209600000;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    private String createToken(User user, long tokenTime) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(user.getUsername()) // 사용자 식별자값(ID)
                        .setExpiration(new Date(date.getTime() + tokenTime)) // 만료 시간
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

    public String createAccessToken(User user) {
        return createToken(user, ACCESS_TOKEN_TIME);
    }

    public String createRefreshToken(User user) {
        return createToken(user, REFRESH_TOKEN_TIME);
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(ACCESS_TOKEN_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String refreshTokenSubstring(String refreshToken) {
        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith(BEARER_PREFIX)) {
            return refreshToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | io.jsonwebtoken.security.SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("{}", "Expired JWT token, 만료된 JWT token 입니다.");
            throw new RuntimeException("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
