package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.LoginDTO;
import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.Service.CorporateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/corporate")
@Tag(name = "Corporate API", description = "기업 사용자 관련 API")
public class CorporateController {

    @Autowired
    CorporateService corporateService;

    @PostMapping("/login")
    @Operation(
            summary = "기업 사용자 로그인",
            description = "기업 사용자로 로그인합니다.",
            requestBody = @RequestBody(
                    description = "로그인 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "로그인 요청 예시",
                                    value = "{\n  \"loginId\": \"corporateUser\",\n  \"loginPw\": \"encryptedPassword\"\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "로그인 성공 예시",
                                            value = "{\n  \"corporateID\": \"1\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "-1",
                            description = "로그인 실패",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "로그인 실패 예시",
                                            value = "{\n  \"corporateID\": \"-1\"\n}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> loginBusiness(@org.springframework.web.bind.annotation.RequestBody LoginDTO loginDTO) {
        Long result = corporateService.loginCorporateUser(loginDTO);
        System.out.println(loginDTO.getLoginId());
        System.out.println(loginDTO.getLoginPw());
        return ResponseEntity.ok(Map.of("corporateID", result.toString()));
    }

    @PostMapping("/register")
    @Operation(
            summary = "기업 사용자 등록",
            description = "기업 사용자를 등록합니다.",
            requestBody = @RequestBody(
                    description = "등록할 기업 사용자 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "등록 요청 예시(다 있어야함)",
                                    value = "{\n" +
                                            "  \"loginId\": \"corporateUser\",\n" +
                                            "  \"loginPw\": \"encryptedPassword\",\n" +
                                            "  \"companyName\": \"Test Company\",\n" +
                                            "  \"contactEmail\": \"test@company.com\",\n" +
                                            "  \"contactPhone\": \"010-1234-5678\",\n" +
                                            "  \"industry\": \"IT\",\n" +
                                            "  \"businessRegistrationNumber\": \"123-45-67890\",\n" +
                                            "  \"managerName\": \"John Doe\",\n" +
                                            "  \"managerPhone\": \"010-1111-2222\",\n" +
                                            "  \"managerEmail\": \"manager@company.com\"\n" +
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
                                            value = "10"
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
    public ResponseEntity<Long> registerBusiness(@org.springframework.web.bind.annotation.RequestBody RegistCorporateUserDTO RegistCorporateUserDTO) {
        long result = corporateService.registCorporateUser(RegistCorporateUserDTO);

        System.out.println(RegistCorporateUserDTO.getIndustry());
        System.out.println(RegistCorporateUserDTO.getContactEmail());
        System.out.println(RegistCorporateUserDTO.getCompanyName());
        System.out.println(RegistCorporateUserDTO.getManagerEmail());
        System.out.println(RegistCorporateUserDTO.getManagerName());
        System.out.println(RegistCorporateUserDTO.getManagerEmail());
        System.out.println(RegistCorporateUserDTO.getManagerPhone());
        System.out.println(RegistCorporateUserDTO.getContactPhone());
        System.out.println(RegistCorporateUserDTO.getContactPhone());


        return ResponseEntity.ok(result); // 기업의 DB index 반환
    }

    @PatchMapping("/{corporate_id}")
    @Operation(
            summary = "기업 사용자 정보 수정",
            description = "기업 사용자의 정보를 수정합니다.",
            requestBody = @RequestBody(
                    description = "수정할 기업 사용자 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "수정 요청 예시",
                                    value = "{\n" +
                                            "  \"loginId\": \"수정된 corporateUser 값\",\n" +
                                            "  \"loginPw\": \"수정된 encryptedPassword값\",\n" +
                                            "  \"companyName\": \"수정된 기업명\",\n" +
                                            "  \"contactEmail\": \"수정된 이메일 값\",\n" +
                                            "  \"contactPhone\": \"수정된 연락처값\",\n" +
                                            "  \"industry\": \"수정된 산업분야\",\n" +
                                            "  \"businessRegistrationNumber\": \"수정된 값 123-45-67890\",\n" +
                                            "  \"managerName\": \"수정된 John Doe\",\n" +
                                            "  \"managerPhone\": \"수정된 010-1111-2222\",\n" +
                                            "  \"managerEmail\": \"수정된 manager@company.com\"\n" +
                                            "}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "수정 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "수정 성공 예시",
                                            value = "{\n  \"message\": \"Corporate information updated successfully\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "-1",
                            description = "해당 기업 사용자 없음",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "수정 실패 예시",
                                            value = "{\n  \"message\": \"failed to find user\"\n}"
                                    )
                            )
                    )

            }
    )
    public ResponseEntity<Map<String, String>> updateCorporate(
            @PathVariable String corporate_id,
            @org.springframework.web.bind.annotation.RequestBody RegistCorporateUserDTO RegistCorporateUserDTO) {
        long result = corporateService.patchCoporateUser(corporate_id, RegistCorporateUserDTO);
        if (result ==-1) {
            return ResponseEntity.ok(Map.of("message", "failed to find user"));
        }
        return ResponseEntity.ok(Map.of("message", "Corporate information updated successfully"));
    }

    @DeleteMapping("/{corporate_id}")
    @Operation(
            summary = "기업 사용자 삭제",
            description = "기업 사용자를 삭제합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "삭제 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "삭제 성공 예시",
                                            value = "{\n  \"message\": \"Corporate deleted successfully\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "해당 기업 사용자 없음",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "삭제 실패 예시",
                                            value = "{\n  \"message\": \"failed to find user\"\n}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> deleteCorporate(@PathVariable String corporate_id) {
        long result = corporateService.deleteCoporateUser(corporate_id);
        if (result ==-1) {
            return ResponseEntity.ok(Map.of("message", "failed to find user"));
        }
        return ResponseEntity.ok(Map.of("message", "Corporate deleted successfully"));
    }
}
