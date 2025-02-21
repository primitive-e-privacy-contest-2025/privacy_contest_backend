package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.ApiCallLogDTO;
import com.primitive.privacy_contest.Service.ApiCallLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/AccessLog")

@Tag(name = "Corporate API", description = "유저별 기업이 조회한 내역 조회")
public class ApiCallLogController {
    @Autowired
    ApiCallLogService apiCallLogService;

    @GetMapping("user/{userId}")
    @Operation(
            summary = "사용자 API 호출 로그 조회",
            description = "해당 사용자에 대해 기업이 조회한 로그 목록을 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ApiCallLogDTO.class))
                    )),
            @ApiResponse(responseCode = "404", description = "로그를 찾을 수 없음", content = @Content)
    })
    public ResponseEntity<List<ApiCallLogDTO>> getApiCallLog(@PathVariable String userId){
        Long userIdL = Long.parseLong(userId);
        List<ApiCallLogDTO> list =apiCallLogService.getApiCallLog(userIdL);
        return ResponseEntity.ok(list);
    }
}
