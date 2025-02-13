package com.primitive.privacy_contest.Controller;

import com.primitive.privacy_contest.DTO.LoginDTO;
import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.Service.CorporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/corporate")
public class CorporateController {
    @Autowired
    CorporateService corporateService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginBusiness(@RequestBody LoginDTO loginDTO) {
        Integer result=corporateService.loginCorporateUser(loginDTO);
        return ResponseEntity.ok(Map.of(
                "corporateID", result.toString()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> registerBusiness(@RequestBody RegistCorporateUserDTO RegistCorporateUserDTO) {
        int result =0;
        result= corporateService.registCorporateUser(RegistCorporateUserDTO);
        if (result<=0){
            return ResponseEntity.ok(-1);
        }
        return ResponseEntity.ok(result);//기업의 db index 반환
    }

    @PatchMapping("/{corporate_id}")
    public ResponseEntity<Map<String, String>> updateCorporate(@PathVariable String corporate_id, @RequestBody RegistCorporateUserDTO RegistCorporateUserDTO) {
        corporateService.patchCoporateUser(corporate_id, RegistCorporateUserDTO);
        return ResponseEntity.ok(Map.of("message", "Corporate information updated successfully"));
    }

    @DeleteMapping("/{corporate_id}")
    public ResponseEntity<Map<String, String>> deleteCorporate(@PathVariable String corporate_id) {
        corporateService.deleteCoporateUser(corporate_id);
        return ResponseEntity.ok(Map.of("message", "Corporate deleted successfully"));
    }
}