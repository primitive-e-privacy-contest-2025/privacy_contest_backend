package com.primitive.privacy_contest.Repository.FileStorages;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileStoragesRepository extends JpaRepository<FileStorages, Long> {

    List<FileStorages> findByUser(UserPersonalInfo user);
}
