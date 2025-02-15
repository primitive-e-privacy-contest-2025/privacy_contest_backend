package com.primitive.privacy_contest.Repository.CorporateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorporateUsersRepository extends JpaRepository<CorporateUsers, Long> {
    List<CorporateUsers> findByLoginId(String loginId);
}