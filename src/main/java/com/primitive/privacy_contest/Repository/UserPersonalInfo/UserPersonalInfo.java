package com.primitive.privacy_contest.Repository.UserPersonalInfo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "UserPersonalInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 사용자 고유 ID

    @Column(nullable = false, unique = true, length = 20)
    private String loginId; // 로그인 전용 ID (중복 불가)

    @Column(nullable = false)
    private String loginPw; // SHA-256 암호화하여 저장되는 비밀번호

    @Column(nullable = false, length = 100)
    private String fullName; // 사용자 실명

    @Column(nullable = false, length = 100)
    private String email; // 사용자 이메일

    @Column(length = 20)
    private String phoneNumber; // 전화번호 (선택 입력)

    private LocalDate dateOfBirth; // 생년월일

    @Column(columnDefinition = "TEXT")
    private String address; // 주소 정보 (TEXT 타입)

    @Column(nullable = false, updatable = false)
    private Timestamp registrationDate; // 가입일 (수정 불가)

    @Enumerated(EnumType.STRING)
    private AccountStatus status; // 계정 상태 (ACTIVE, INACTIVE, SUSPENDED)

    @Column(length = 255)
    private String googleUserId; // Google OAuth2 연동용 ID
}
