package com.primitive.privacy_contest.Repository.CorporateUsers;

import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "CorporateUsers")
public class CorporateUsers {
    public CorporateUsers(RegistCorporateUserDTO R){
        this.loginId=R.getLoginId();
        this.loginPw=R.getLoginPw();
        this.companyName=R.getCompanyName();
        this.contactEmail=R.getContactEmail();
        this.contactPhone=R.getContactPhone();
        this.industry=R.getIndustry();
        this.businessRegistrationNumber=R.getBusinessRegistrationNumber();
        this.managerName=R.getManagerName();
        this.managerEmail=R.getManagerEmail();
        this.managerPhone=R.getManagerPhone();
    }
    public void patch(RegistCorporateUserDTO R){
        this.loginId=R.getLoginId();
        this.loginPw=R.getLoginPw();
        this.companyName=R.getCompanyName();
        this.contactEmail=R.getContactEmail();
        this.contactPhone=R.getContactPhone();
        this.industry=R.getIndustry();
        this.businessRegistrationNumber=R.getBusinessRegistrationNumber();
        this.managerName=R.getManagerName();
        this.managerEmail=R.getManagerEmail();
        this.managerPhone=R.getManagerPhone();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long corporateId; // 기업 고유 ID (PK)

    @Column(nullable = false, unique = true, length = 20)
    private String loginId;

    @Column(nullable = false)
    private String loginPw; // SHA-256 암호화 저장

    @Column(nullable = false, length = 100)
    private String companyName; // 회사 이름

    @Column(nullable = false, length = 100, unique = true)
    private String contactEmail; // 회사 대표 이메일

    @Column(nullable = false, length = 20)
    private String contactPhone; // 회사 대표 전화번호

    @Column(columnDefinition = "TEXT")
    private String industry; // 산업분야

    @Column(nullable = false, unique = true)
    private String businessRegistrationNumber; // 사업자 등록번호

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now(); // 회사 등록일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CompanyStatus companyStatus= CompanyStatus.ACTIVE; // 기업 상태 (ENUM)

    @Column(nullable = false, length = 100)
    private String managerName; // 담당자 이름

    @Column(nullable = false, length = 100)
    private String managerPhone; // 담당자 연락처

    @Column(nullable = false, length = 100, unique = true)
    private String managerEmail; // 담당자 이메일
}

