package com.primitive.privacy_contest.Repository.UserServiceAccess;

public enum AccessStatus {
    GRANTED,  // 접근 허가됨
    DENIED,   // 접근 거부됨
    PENDING   // 접근 요청 대기 중
}