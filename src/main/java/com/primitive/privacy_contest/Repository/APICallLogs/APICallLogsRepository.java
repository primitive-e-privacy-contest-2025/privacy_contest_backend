package com.primitive.privacy_contest.Repository.APICallLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APICallLogsRepository extends JpaRepository<APICallLogs, Long> {
}