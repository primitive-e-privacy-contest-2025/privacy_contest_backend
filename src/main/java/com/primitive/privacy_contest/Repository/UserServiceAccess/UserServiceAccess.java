package com.primitive.privacy_contest.Repository.UserServiceAccess;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user_service_access")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserServiceAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Long accessId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPersonalInfo user; // 사용자 정보 (FK)

    @Column(name = "service_id", nullable = false)
    private Long serviceId; // 서비스 ID

    @Enumerated(EnumType.STRING)
    @Column(name = "access_status")
    private AccessStatus accessStatus; // 접근 상태 (GRANTED, DENIED, PENDING)

    @Column(name = "access_granted")
    private boolean accessGranted;

    @Column(name = "access_started")
    private Timestamp accessStarted;

    @Column(name = "access_ended")
    private Timestamp accessEnded;

    @Column(name = "last_access_time")
    private Timestamp lastAccessTime;
}