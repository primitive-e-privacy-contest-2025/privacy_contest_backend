package com.primitive.privacy_contest.Repository.Services;

import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findByCorporateUsers(CorporateUsers corporateUsers);
}
