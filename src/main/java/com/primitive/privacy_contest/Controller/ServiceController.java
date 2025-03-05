package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.RegistServiceDTO;
import com.primitive.privacy_contest.DTO.ServiceRegistDTO;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody; // Swagger 문서화용
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
@Tag(name = "Service API", description = "서비스 등록 및 조회 관련 API")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("/register")
    @Operation(
            summary = "서비스 등록",
            description = "새 서비스를 등록합니다. 요청 바디에 RegistServiceDTO 객체로 등록 정보를 보내면, 등록된 서비스의 ID와 API Key를 포함한 ServiceRegistDTO 객체가 반환됩니다.",
            requestBody = @RequestBody(
                    description = "등록할 서비스 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "서비스 등록 요청 예시",
                                    value = "{\n  \"corporateUserID\": \"1\",\n  \"serviceName\": \"Test Service\",\n  \"description\": \"서비스 설명\"\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "서비스 등록 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "서비스 등록 성공 예시",
                                            value = "{\n  \"serviceId\": \"10\",\n  \"APIkey\": \"232321312312321312312\"\n}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "서비스 등록 실패"
                    )
            }
    )
    public ResponseEntity<ServiceRegistDTO> registerService(@org.springframework.web.bind.annotation.RequestBody RegistServiceDTO registServiceDTO) {
        ServiceRegistDTO serviceRegistDTO = servicesService.registerService(registServiceDTO);
        return ResponseEntity.ok(serviceRegistDTO);
    }

    @GetMapping("corp/{corporateId}")
    @Operation(
            summary = "서비스 조회",
            description = "특정 기업(corporate_id)에 속한 서비스 목록을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "서비스 목록 조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "서비스 조회 예시",
                                            value = "[\n  { \"serviceId\": 10, \"serviceName\": \"Test Service\", \"description\": \"서비스 설명\", \"apiKey\": \"232321312312321312312\" },\n  { \"serviceId\": 11, \"serviceName\": \"Another Service\", \"description\": \"다른 서비스 설명\", \"apiKey\": \"ABC123456789\" }\n]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "서비스 목록 조회 실패"
                    )
            }
    )
    public ResponseEntity<List<Services>> getServices(@PathVariable("corporate_id") Long corporateId) {
        List<Services> services = servicesService.getServiceByCorp(corporateId);
        return ResponseEntity.ok(services);
    }

    /*@PatchMapping("/{service_id}/reset-key")
    @Operation(
            summary = "API 키 재설정",
            description = "특정 서비스의 API 키를 재설정합니다. (현재는 미구현 상태로, 예시 메시지와 함께 새로운 API 키가 반환됩니다.)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "API 키 재설정 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "API 키 재설정 예시",
                                            value = "{\n  \"message\": \"API key reset\",\n  \"new_api_key\": \"NEW_API_KEY_67890\"\n}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> resetApiKey(@PathVariable Long service_id) {
        return ResponseEntity.ok(Map.of(
                "message", "API key reset",
                "new_api_key", "NEW_API_KEY_67890"
        ));
    }*/
}
