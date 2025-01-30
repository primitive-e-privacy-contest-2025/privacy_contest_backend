package com.primitive.privacy_contest.Repository.FileStorages;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "file_storage")
public class FileStorages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId; // 파일 고유 ID (PK, 자동 증가)

    @Column(nullable = false)
    private Integer userId; // 파일 소유 사용자 ID (FK)

    @Column(nullable = false)
    private Integer serviceId; // 파일 소유 서비스 ID (FK)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String filePath; // 저장된 파일 경로

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 파일 생성 시간 (기본값: 현재 시간)
}
