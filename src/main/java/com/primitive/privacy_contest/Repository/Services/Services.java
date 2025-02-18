package com.primitive.privacy_contest.Repository.Services;

import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId; // 서비스 고유 ID (PK)

    @Column(nullable = false, length = 100)
    private String serviceName; // 서비스 이름

    @ManyToOne
    @JoinColumn(nullable = false) // 기업 사용자와 연결 (FK)
    private CorporateUsers corporateUsers;

    @Column(columnDefinition = "TEXT")
    private String description; // 서비스 설명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ServiceStatus status=ServiceStatus.ACTIVE; // 서비스 상태 (ENUM)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 서비스 생성일 (기본값: 현재 시간)

    @Column(nullable = false, length = 255, unique = true)
    private String apiKey; // API 키 (외부 요청 시 필요)
}
