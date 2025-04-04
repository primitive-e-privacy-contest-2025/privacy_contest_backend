package com.primitive.privacy_contest.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "사용자 활동 정보를 담는 DTO")
public class ActivityDTO {

    @Schema(description = "활동 타입 (예: 로그인, 조회, 업로드)", example = "로그인")
    private String activityType;

    @Schema(description = "활동 관련 추가 메타데이터", example = "\"metadata\": \"{\"service\":\"testService\",\"result\":\"성공적으로 구매함\",\"item\":\"33341224\",\"itemName\":\"맛있닭 닭가슴살 50인분\"")
    private String metadata;
}
