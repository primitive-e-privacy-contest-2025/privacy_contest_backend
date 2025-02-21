package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.ActivityDTO;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Repository.Services.ServicesRepository;
import com.primitive.privacy_contest.Service.ServicesService;
import com.primitive.privacy_contest.Service.UserActivityService;
import com.primitive.privacy_contest.Repository.UserActivity.UserActivity;
import com.primitive.privacy_contest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;  // 문서화용

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{userId}/services/{serviceId}/activity")
@Tag(name = "User Activity API", description = "사용자 활동 이력 관련 API")
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;
    @GetMapping
    @Operation(
            summary = "사용자 활동 이력 조회",
            description = "특정 사용자와 서비스에 대한 활동 이력을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "활동 이력 조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserActivity.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "활동 이력이 존재하지 않음")
            }
    )
    public ResponseEntity<List<UserActivity>> getUserActivity(@PathVariable Long userId, @PathVariable Long serviceId) {
        return ResponseEntity.ok(userActivityService.getUserActivity(userId, serviceId));
    }

    @Operation(
            summary = "사용자 활동 조회",
            description = "특정 사용자 및 서비스에 대한 활동 로그를 API Key와 함께 조회합니다."

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "활동 로그를 찾을 수 없음", content = @Content)
    })
    @GetMapping("/corp")
    public List<UserActivity> getUserActivityByCorp(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "서비스 ID", example = "1") @PathVariable Long serviceId,
            @Parameter(description = "API Key", example = "07eef2db369d1812f9c981607f121995")
            @RequestHeader("11API-KEY") String apiKey
    ) {
        return userActivityService.getUserActivityByCorp(userId, serviceId, apiKey);
    }

    @Operation(
            summary = "사용자 활동 등록",
            description = "특정 사용자와 서비스에 대한 활동 로그를 등록합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 등록",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 입력", content = @Content)
    })
    @PutMapping
    public ResponseEntity<String> putUserActivity(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "서비스 ID", example = "1") @PathVariable Long serviceId,
            @RequestBody(
                    description = "활동 정보 DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActivityDTO.class),
                            examples = @ExampleObject(name = "ActivityDTO", value = "{\"activityType\": \"로그인\", \"metadata\": \"성공적으로 로그인함\"}")
                    )
            ) @org.springframework.web.bind.annotation.RequestBody ActivityDTO activityDTO
    ) {
        userActivityService.putUserActivity(userId, serviceId, activityDTO);
        return ResponseEntity.ok("✅ 성공적으로 등록됨");

    }


    @DeleteMapping("/delete")
    @Operation(
            summary = "특정 활동 이력 삭제",
            description = "특정 사용자와 서비스의 활동 이력 중, 선택한 activity_ids에 해당하는 이력을 삭제합니다.",
            requestBody = @RequestBody(
                    description = "삭제할 활동 ID 목록",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "삭제 요청 예시",
                                    value = "{\n  \"activity_ids\": [1, 2, 3]\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "활동 이력 삭제 성공",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject(value = "✅ 활동 이력이 삭제되었습니다.")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    public ResponseEntity<String> deleteUserActivity(
            @PathVariable Long userId,
            @PathVariable Long serviceId,
            @org.springframework.web.bind.annotation.RequestBody Map<String, List<Long>> requestBody) {
        List<Long> activityIds = requestBody.get("activity_ids");
        userActivityService.deleteUserActivity(userId, serviceId, activityIds);
        return ResponseEntity.ok("✅ 활동 이력이 삭제되었습니다.");
    }
}
