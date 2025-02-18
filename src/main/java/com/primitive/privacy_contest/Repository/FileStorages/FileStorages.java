package com.primitive.privacy_contest.Repository.FileStorages;

import com.primitive.privacy_contest.Repository.Services.Services;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "FileStorages")
public class FileStorages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId; // 파일 고유 ID (PK, 자동 증가)

    @ManyToOne
    @JoinColumn(name = "user", nullable = false) // 사용자 테이블과 연결 (FK)
    private UserPersonalInfo user;

    @ManyToOne
    @JoinColumn(name = "services", nullable = false) // 서비스 테이블과 연결 (FK)
    private Services service;

    @Lob
    @Column(nullable = false)
    private byte[] fileData; // 저장된 파일을 BLOB으로 저장

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 파일 생성 시간 (기본값: 현재 시간)
}
