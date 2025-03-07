package com.primitive.privacy_contest.Repository.APICallLogs;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APICallLogsRepository extends JpaRepository<APICallLogs, Long> {
    List<APICallLogs> findByUser(UserPersonalInfo user);

}