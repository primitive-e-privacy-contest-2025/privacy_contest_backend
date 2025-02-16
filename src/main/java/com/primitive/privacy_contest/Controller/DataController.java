package com.primitive.privacy_contest.Controller;
import com.primitive.privacy_contest.Service.CorporateService;
import com.primitive.privacy_contest.Service.DataService;
import com.primitive.privacy_contest.Service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    CorporateService corporateService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    DataService dataService;



    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> getData(@PathVariable Long user_id) {
        return ResponseEntity.ok(Map.of(
                "user_id", user_id,
                "email", "test@example.com",
                "phone_number", "01012345678"
        ));
    }

    @PostMapping("/{user_id}/{service_id}/activity")
    public ResponseEntity<Map<String, String>> storeUserActivity(@PathVariable Long user_id, @PathVariable Long service_id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of(
                "message", "CSV file created successfully",
                "file_path", "/data_storage/" + user_id + "/" + service_id + "_activity_data.csv"
        ));
    }

    @GetMapping("/{user_id}/{service_id}/activity")
    public ResponseEntity<List<Map<String, Object>>> getUserActivity(@PathVariable Long user_id, @PathVariable Long service_id) {
        return ResponseEntity.ok(List.of(
                Map.of("timestamp", "2025-01-18T12:34:56Z", "activity_type", "page_view", "metadata", Map.of("page", "/home", "duration", 120)),
                Map.of("timestamp", "2025-01-18T13:45:12Z", "activity_type", "purchase", "metadata", Map.of("item_id", "123", "price", 3000))
        ));
    }
}

