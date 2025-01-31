package com.primitive.privacy_contest.Repository.CorporateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateUsersRepository extends JpaRepository<CorporateUsers, Integer> {
}