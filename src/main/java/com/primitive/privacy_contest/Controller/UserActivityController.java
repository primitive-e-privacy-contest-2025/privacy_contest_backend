package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.Service.UserActivityService;
import com.primitive.privacy_contest.Repository.UserActivity.UserActivity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;  // 문서화용

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{userId}/services/{serviceId}/activity")
@Tag(name = "User Activity API", description = "사용자 활동 이력 관련 API")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

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
