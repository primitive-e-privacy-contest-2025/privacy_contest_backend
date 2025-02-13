package com.primitive.privacy_contest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private String loginId;
    private String loginPw; // SHA-256 암호화 저장
}
