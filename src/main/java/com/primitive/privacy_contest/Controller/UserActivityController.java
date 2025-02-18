package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.Repository.UserServices.UserActivityService;  // 패키지 경로 수정
import com.primitive.privacy_contest.Repository.UserActivity.UserActivity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{userId}/services/{serviceId}/activity")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    //사용자의 특정 서비스 활동 이력 조회
    @GetMapping
    public ResponseEntity<List<UserActivity>> getUserActivity(@PathVariable Long userId, @PathVariable Long serviceId) {
        return ResponseEntity.ok(userActivityService.getUserActivity(userId, serviceId));
    }

    //특정 활동 이력 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserActivity(@PathVariable Long userId,
                                                     @PathVariable Long serviceId,
                                                     @RequestBody Map<String, List<Long>> requestBody) {
        List<Long> activityIds = requestBody.get("activity_ids");
        userActivityService.deleteUserActivity(userId, serviceId, activityIds);
        return ResponseEntity.ok("✅ 활동 이력이 삭제되었습니다.");
    }
}
