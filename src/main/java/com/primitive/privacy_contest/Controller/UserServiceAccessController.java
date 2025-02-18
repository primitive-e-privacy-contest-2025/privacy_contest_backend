package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Repository.UserServices.UserServiceAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/services")
public class UserServiceAccessController {

    private final UserServiceAccessService userServiceAccessService;

    public UserServiceAccessController(UserServiceAccessService userServiceAccessService) {
        this.userServiceAccessService = userServiceAccessService;
    }

    //사용자의 서비스 접근 권한 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserServiceAccess>> getUserServices(@PathVariable Long userId) {
        return ResponseEntity.ok(userServiceAccessService.getUserServices(userId));
    }

    //사용자가 특정 서비스 접근을 허용
    @PostMapping("/{userId}/grant/{serviceId}")
    public ResponseEntity<String> grantAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        userServiceAccessService.grantAccess(userId, serviceId);
        return ResponseEntity.ok("✅ 서비스 접근 권한 허가 완료");
    }

    //사용자가 특정 서비스 접근을 거부
    @PostMapping("/{userId}/deny/{serviceId}")
    public ResponseEntity<String> denyAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        userServiceAccessService.denyAccess(userId, serviceId);
        return ResponseEntity.ok("❌ 서비스 접근 권한 거부 완료");
    }
}
