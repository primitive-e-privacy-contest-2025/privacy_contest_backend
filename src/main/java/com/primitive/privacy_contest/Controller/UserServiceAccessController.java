package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Service.UserServiceAccessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/services")
@Tag(name = "User Service Access API", description = "사용자 서비스 접근 권한 관리 API")
public class UserServiceAccessController {

    private final UserServiceAccessService userServiceAccessService;

    public UserServiceAccessController(UserServiceAccessService userServiceAccessService) {
        this.userServiceAccessService = userServiceAccessService;
    }
    @PostMapping("/{userId}/request/{serviceId}")
    @Operation(
            summary = "서비스 접근 요청 생성",
            description = "특정 서비스(serviceId)의 관리자가 지정된 사용자(userId)에게 접근 요청을 생성하는데에 사용.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "서비스 접근 요청 생성 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"✅ 서비스 접근 요청생성\"")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청 또는 요청 생성 실패"
                    )
            }
    )
    public ResponseEntity<String> requestAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        userServiceAccessService.requestAccess(userId, serviceId);
        return ResponseEntity.ok("✅ 서비스 접근 요청생성");
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "사용자의 서비스 접근 권한 목록 조회",
            description = "특정 사용자(userId)의 서비스 접근 권한 목록을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "[\n  { \"userId\": 1, \"serviceId\": 2, \"access\": true },\n  { \"userId\": 1, \"serviceId\": 3, \"access\": false }\n]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "사용자 또는 권한 정보 없음"
                    )
            }
    )
    public ResponseEntity<List<UserServiceAccess>> getUserServices(@PathVariable Long userId) {
        return ResponseEntity.ok(userServiceAccessService.getUserServices(userId));
    }

    @PostMapping("/{userId}/grant/{serviceId}")
    @Operation(
            summary = "서비스 접근 권한 허가",
            description = "특정 사용자(userId)에게 특정 서비스(serviceId)의 접근 권한을 허가합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "서비스 접근 권한 허가 완료",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "\"✅ 서비스 접근 권한 허가 완료\""
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "허가 실패"
                    )
            }
    )
    public ResponseEntity<String> grantAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        userServiceAccessService.grantAccess(userId, serviceId);
        return ResponseEntity.ok("✅ 서비스 접근 권한 허가 완료");
    }

    @PostMapping("/{userId}/deny/{serviceId}")
    @Operation(
            summary = "서비스 접근 권한 거부",
            description = "특정 사용자(userId)에게 특정 서비스(serviceId)의 접근 권한을 거부합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "서비스 접근 권한 거부 완료",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "\"❌ 서비스 접근 권한 거부 완료\""
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "거부 실패"
                    )
            }
    )
    public ResponseEntity<String> denyAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        userServiceAccessService.denyAccess(userId, serviceId);
        return ResponseEntity.ok("❌ 서비스 접근 권한 거부 완료");
    }
}
