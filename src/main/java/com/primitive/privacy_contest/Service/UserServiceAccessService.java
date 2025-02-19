package com.primitive.privacy_contest.Service;

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

    public long requestAccess(Long serviceId, Long user_id) {
        try {
            UserPersonalInfo user = userRepository.findById(user_id).get();
            UserServiceAccess userServiceAccess = new UserServiceAccess();
            userServiceAccess.setServiceId(serviceId);
            userServiceAccess.setUser(user);
            userServiceAccess.setAccessStatus(AccessStatus.PENDING);
            userServiceAccessRepository.save(userServiceAccess);
            return userServiceAccess.getAccessId();
        } catch (Exception e) {
            return -1;
        }
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
        //todo 기존것 찾아서 수정 또는 없는 요청이면 허가 새로 생성
        //todo 요청생성시에도 기존 수락이 되어있으면 새로  생성 x
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
