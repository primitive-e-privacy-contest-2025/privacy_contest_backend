package com.primitive.privacy_contest.Controller;
import com.primitive.privacy_contest.DTO.RegistServiceDTO;
import com.primitive.privacy_contest.DTO.ServiceRegistDTO;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Severity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;

    @PostMapping("/register")
    public ResponseEntity<ServiceRegistDTO> registerService(@RequestBody RegistServiceDTO registServiceDTO) {
        ServiceRegistDTO serviceRegistDTO=servicesService.registerService(registServiceDTO);
        return ResponseEntity.ok(serviceRegistDTO);
    }

    @GetMapping
    public ResponseEntity<List> getServices(@RequestParam("corporate_id") Long corporateId) {
        List<Services> services =servicesService.getService(corporateId);
        return ResponseEntity.ok(services);
    }

    @PatchMapping("/{service_id}/reset-key")//api key 교체 미구현
    public ResponseEntity<Map<String, String>> resetApiKey(@PathVariable Long service_id) {
        return ResponseEntity.ok(Map.of(
                "message", "API key reset",
                "new_api_key", "NEW_API_KEY_67890"
        ));
    }
}