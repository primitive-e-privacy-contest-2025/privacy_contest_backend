package com.primitive.privacy_contest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistCorporateUserDTO {
    private String loginId;

    private String loginPw; // SHA-256 암호화 저장

    private String companyName; // 회사 이름

    private String contactEmail; // 회사 대표 이메일

    private String contactPhone; // 회사 대표 전화번호

    private String industry; // 산업분야

    private String businessRegistrationNumber; // 사업자 등록번호

    private String managerName; // 담당자 이름

    private String managerPhone; // 담당자 연락처

    private String managerEmail; // 담당자 이메일
}
