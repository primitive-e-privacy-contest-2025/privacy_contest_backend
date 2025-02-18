package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.DTO.RegistUserDTO;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User API", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(
            summary = "사용자 정보 조회",
            description = "주어진 userId에 해당하는 사용자의 정보를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 정보 조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "사용자 정보 예시",
                                            value = "{\n  \"userId\": 1,\n  \"name\": \"홍길동\",\n  \"email\": \"hong@example.com\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없음")
            }
    )
    public ResponseEntity<UserPersonalInfo> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @PostMapping("/register")
    @Operation(
            summary = "일반 사용자 등록",
            description = "일반 사용자를 등록합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "등록할 일반 사용자 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "등록 요청 예시(다 있어야함)",
                                    value = "{\n" +
                                            "  \"loginId\": \"testUser\",\n" +
                                            "  \"loginPw\": \"encryptedPassword\",\n" +
                                            "  \"fullName\": \"홍길동\",\n" +
                                            "  \"email\": \"hong@example.com\",\n" +
                                            "  \"phoneNumber\": \"010-1234-5678\",\n" +
                                            "  \"dateOfBirth\": \"1990-01-01\",\n" +
                                            "  \"address\": \"서울시 강남구 테헤란로 123\",\n" +
                                            "}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "등록 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "등록 성공 예시",
                                            value = "10(유저의 고유 id(로그인아이디 아님))"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "-1",
                            description = "등록 실패",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "등록 실패 예시",
                                            value = "-1"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Long> registerUser(@org.springframework.web.bind.annotation.RequestBody RegistUserDTO registUserDTO) {
        long result = userService.createUser(registUserDTO);

        return ResponseEntity.ok(result); // 기업의 DB index 반환
    }


    @PatchMapping("/{userId}")
    @Operation(
            summary = "사용자 정보 수정",
            description = "주어진 userId에 해당하는 사용자의 정보를 수정합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 정보 수정 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "수정 후 사용자 정보 예시",
                                            value = "{\n" +
                                                    "  \"userId\": \"1\",\n" +
                                                    "  \"loginId\": \"testUser\",\n" +
                                                    "  \"loginPw\": \"encryptedPassword\",\n" +
                                                    "  \"fullName\": \"홍길동\",\n" +
                                                    "  \"email\": \"hong@example.com\",\n" +
                                                    "  \"phoneNumber\": \"010-1234-5678\",\n" +
                                                    "  \"dateOfBirth\": \"1990-01-01\",\n" +
                                                    "  \"address\": \"서울시 강남구 테헤란로 123\",\n" +
                                                    "  \"status\": \"ACTIVE\",\n" +
                                                    "  \"googleUserId\": \"null\",\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없음")
            }
    )
    public ResponseEntity<UserPersonalInfo> updateUser(
            @PathVariable Long userId,
            @RequestBody RegistUserDTO updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    @Operation(
            summary = "사용자 삭제",
            description = "주어진 userId에 해당하는 사용자를 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "사용자 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없음")
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
