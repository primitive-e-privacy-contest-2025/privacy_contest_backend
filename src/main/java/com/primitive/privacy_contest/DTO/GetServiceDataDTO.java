package com.primitive.privacy_contest.DTO;

import com.primitive.privacy_contest.Repository.Services.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class GetServiceDataDTO {
    private Integer serviceId; // 서비스 고유 ID (PK)
    private String serviceName; // 서비스 이름
    private String corporateUserID;//인덱스
    private String description; // 서비스 설명
    private ServiceStatus status; // 서비스 상태 (ENUM)
    private LocalDateTime createdAt = LocalDateTime.now(); // 서비스 생성일 (기본값: 현재 시간)
    private String apiKey; // API 키 (외부 요청 시 필요)
}
