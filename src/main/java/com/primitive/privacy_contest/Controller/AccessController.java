package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.NewRequestDto;
import com.primitive.privacy_contest.Service.AccessService;
import com.primitive.privacy_contest.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/data")
public class AccessController {
    @Autowired
    private AccessService accessService;

    @PostMapping("/request")
    public ResponseEntity<Map<String, Object>> requestData(@RequestHeader String ApiKey,@RequestBody NewRequestDto dto) {
        long result = accessService.createRequest(ApiKey,dto.getServiceId(),dto.getUserId());
        return ResponseEntity.ok(Map.of("message",result));
    }
    @PostMapping("/{user_id}/grant")
    public ResponseEntity<Map<String, String>> grantRequest(@PathVariable Long user_id, @RequestBody String requestId) {
        accessService.grantRequest(user_id,requestId);
        return ResponseEntity.ok(Map.of("message", "Corporate information updated successfully"));
    }
    @PostMapping("/{user_id}/deny")
    public ResponseEntity<Map<String, String>> denyRequest(@PathVariable Long user_id, @RequestBody String requestId) {
        accessService.rejectRequest(user_id,requestId);
        return ResponseEntity.ok(Map.of("message", "Corporate information updated successfully"));
    }
}
