package com.primitive.privacy_contest.Repository.UserServices;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import com.primitive.privacy_contest.Repository.UserServiceAccess.AccessStatus;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccessRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceAccessService {

    private final UserServiceAccessRepository userServiceAccessRepository;
    private final UserPersonalInfoRepository userRepository;

    public UserServiceAccessService(UserServiceAccessRepository userServiceAccessRepository, UserPersonalInfoRepository userRepository) {
        this.userServiceAccessRepository = userServiceAccessRepository;
        this.userRepository = userRepository;
    }

    //특정 사용자의 서비스 접근 권한 목록 조회
    public List<UserServiceAccess> getUserServices(Long userId) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userServiceAccessRepository.findByUser(user);
    }

    //특정 서비스 접근 허용
    public void grantAccess(Long userId, Long serviceId) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserServiceAccess access = UserServiceAccess.builder()
                .user(user)
                .serviceId(serviceId)
                .accessStatus(AccessStatus.GRANTED)
                .accessGranted(true)
                .accessStarted(new Timestamp(System.currentTimeMillis()))
                .build();

        userServiceAccessRepository.save(access);
    }

    //특정 서비스 접근 거부
    public void denyAccess(Long userId, Long serviceId) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserServiceAccess access = UserServiceAccess.builder()
                .user(user)
                .serviceId(serviceId)
                .accessStatus(AccessStatus.DENIED)
                .accessGranted(false)
                .build();

        userServiceAccessRepository.save(access);
    }
}
