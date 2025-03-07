package com.primitive.privacy_contest.Repository.UserActivity;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByUserAndServiceId(UserPersonalInfo user, Long serviceId);
}
