package com.primitive.privacy_contest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceRegistDTO {
    private String serviceId;//등록된 인덱스 값
    private String APIkey;
}
