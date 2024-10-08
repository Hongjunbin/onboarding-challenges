package com.sparta.onboardingchallenges.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String refreshToken;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Authorities authorities;

    @Builder
    public User(String username, String encryptedPassword, String nickname, Authorities authorities) {
        this.username = username;
        this.password = encryptedPassword;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
