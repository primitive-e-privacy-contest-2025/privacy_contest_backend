package com.primitive.privacy_contest.Repository.UserPersonalInfo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "User_Personal_Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // DB 컬럼명과 매칭
    private Long userId;

    @Column(name = "login_id", nullable = false, unique = true, length = 20)
    private String loginId;

    @Column(name = "login_pw", nullable = false)
    private String loginPw;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "registration_date", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now(); // 유저 가입일

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "status")
    private AccountStatus status=AccountStatus.ACTIVE;

    @Column(name = "google_user_id", length = 255,nullable = true)
    private String googleUserId;
}