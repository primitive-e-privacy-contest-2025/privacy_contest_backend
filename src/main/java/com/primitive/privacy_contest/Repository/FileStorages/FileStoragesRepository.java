package com.primitive.privacy_contest.Repository.FileStorages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStoragesRepository extends JpaRepository<FileStorages, Long> {
}
