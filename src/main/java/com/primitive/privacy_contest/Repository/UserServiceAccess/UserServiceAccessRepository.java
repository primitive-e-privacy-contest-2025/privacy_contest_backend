package com.primitive.privacy_contest.Repository.UserServiceAccess;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceAccessRepository extends JpaRepository<UserServiceAccess, Long> {
    List<UserServiceAccess> findByUser(UserPersonalInfo user);
}