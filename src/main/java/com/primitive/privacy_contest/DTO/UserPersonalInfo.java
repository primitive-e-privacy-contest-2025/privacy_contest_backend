package com.primitive.privacy_contest.DTO;

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
    private Long userId;

    @Column(nullable = false, unique = true, length = 20)
    private String loginId;

    @Column(nullable = false)
    private String loginPw; // SHA-256 암호화 저장

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private LocalDate dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false, updatable = false)
    private Timestamp registrationDate;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(length = 255)
    private String googleUserId;
}
