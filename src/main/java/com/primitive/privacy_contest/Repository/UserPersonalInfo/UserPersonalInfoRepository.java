package com.primitive.privacy_contest.Repository.UserPersonalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersonalInfoRepository extends JpaRepository<UserPersonalInfo, Long> {
    Optional<UserPersonalInfo> findByLoginId(String loginId);
}
