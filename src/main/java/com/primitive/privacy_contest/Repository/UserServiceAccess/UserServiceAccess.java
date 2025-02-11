package com.primitive.privacy_contest.Repository.UserServiceAccess;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "UserServiceAccess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserServiceAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId; // 서비스 접근 권한 ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPersonalInfo user; // 사용자 정보 (FK)

    @Column(nullable = false)
    private Long serviceId; // 서비스 소유 기업 ID

    @Enumerated(EnumType.STRING)
    private AccessStatus accessStatus; // 접근 상태 (GRANTED, DENIED, PENDING)

    private boolean accessGranted; // 접근 허용 여부 (TRUE/FALSE)

    private Timestamp accessStarted; // 서비스 접근 권한 부여 시작일
    private Timestamp accessEnded; // 서비스 접근 권한 종료일 (유효기간)
    private Timestamp lastAccessTime; // 마지막 서비스 접근 시간
}