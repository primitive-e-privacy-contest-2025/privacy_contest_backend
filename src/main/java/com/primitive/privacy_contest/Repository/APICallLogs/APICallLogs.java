package com.primitive.privacy_contest.Repository.APICallLogs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "api_call_logs")
public class APICallLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId; // 로그 ID (PK, 자동 증가)

    @Column(nullable = false)
    private Integer serviceId; // 호출한 서비스 ID (FK)

    @Column(nullable = false)
    private Integer userId; // 요청을 보낸 사용자 ID (FK)

    @Column(nullable = false, length = 255)
    private String endpoint; // 호출된 API 엔드포인트

    @Column(nullable = false, updatable = false)
    private LocalDateTime requestTime = LocalDateTime.now(); // 요청 시간 (기본값: 현재 시간)

    @Column(nullable = false)
    private Integer responseStatus; // API 호출의 응답 상태 코드 (200, 404 등)

    @Column(columnDefinition = "TEXT")
    private String requestBody; // 요청 데이터 (JSON 등)

    @Column(columnDefinition = "TEXT")
    private String responseBody; // 응답 데이터 (JSON 등)

    @Column
    private Long duration; // API 응답 시간(ms)
}
