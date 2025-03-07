package com.primitive.privacy_contest.DTO;

import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "API 호출 로그 데이터 전송 객체")
public class ApiCallLogDTO {

    @Schema(description = "서비스 이름", example = "Test Service")
    private String service;

    @Schema(description = "사용자 정보", example = "testUser")
    private String user;

    @Schema(description = "API 엔드포인트", example = "/api/v1/test")
    private String endpoint;

    @Schema(description = "요청 시간", example = "2025-02-19T19:51:46")
    private LocalDateTime requestTime;

    @Schema(description = "응답 상태", example = "200")
    private int responseStatus;

    @Schema(description = "응답 본문", example = "{\"result\":\"success\"}")
    private String responseAbstract;
}
