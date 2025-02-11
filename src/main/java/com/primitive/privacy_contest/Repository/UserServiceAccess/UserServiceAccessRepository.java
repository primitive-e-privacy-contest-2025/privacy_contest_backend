package com.primitive.privacy_contest.Repository.UserServiceAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceAccessRepository extends JpaRepository<UserServiceAccess, Long> {
    List<UserServiceAccess> findByUser_UserId(Long userId);
}
