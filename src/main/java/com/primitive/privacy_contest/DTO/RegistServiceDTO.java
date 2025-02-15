package com.primitive.privacy_contest.DTO;

import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.Services.ServiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RegistServiceDTO {
    private String corporateUserID;//인덱스 값'
    private String serviceName; // 서비스 이름
    private String description; // 서비스 설명}
}
