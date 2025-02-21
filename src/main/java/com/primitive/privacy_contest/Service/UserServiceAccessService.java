package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import com.primitive.privacy_contest.Repository.UserServiceAccess.AccessStatus;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccessRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceAccessService {

    private final UserServiceAccessRepository userServiceAccessRepository;
    private final UserPersonalInfoRepository userRepository;

    public UserServiceAccessService(UserServiceAccessRepository userServiceAccessRepository, UserPersonalInfoRepository userRepository) {
        this.userServiceAccessRepository = userServiceAccessRepository;
        this.userRepository = userRepository;
    }

    public long requestAccess(Long userId,Long serviceId) {
        try {
            UserPersonalInfo user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            List<UserServiceAccess> accessList = userServiceAccessRepository.findByUser(user);
            UserServiceAccess userServiceAccess = null;
            for (int i = 0; i < accessList.size(); i++) {
                if (accessList.get(i).getServiceId()==serviceId){
                    userServiceAccess=accessList.get(i);
                    break;
                }
            }
            if (userServiceAccess == null){
                userServiceAccess = new UserServiceAccess();
                userServiceAccess.setServiceId(serviceId);
                userServiceAccess.setUser(user);
                userServiceAccess.setAccessStatus(AccessStatus.PENDING);
                userServiceAccessRepository.save(userServiceAccess);
            }
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
        List<UserServiceAccess> accessList = userServiceAccessRepository.findByUser(user);
        UserServiceAccess userServiceAccess = null;
        for (int i = 0; i < accessList.size(); i++) {
            if (accessList.get(i).getServiceId()==serviceId){
                userServiceAccess=accessList.get(i);
                break;
            }
        }
        if (userServiceAccess != null){
            if (userServiceAccess.getServiceId().equals(serviceId)){
                userServiceAccess.setAccessGranted(true);
                userServiceAccess.setAccessStatus(AccessStatus.GRANTED);
                userServiceAccess.setAccessStarted(LocalDateTime.now());
            }
        }
        else {
            userServiceAccess = UserServiceAccess.builder()
                    .user(user)
                    .serviceId(serviceId)
                    .accessStatus(AccessStatus.GRANTED)
                    .accessGranted(true)
                    .accessStarted(LocalDateTime.now())
                    .build();
        }

        userServiceAccessRepository.save(userServiceAccess);
    }

    //특정 서비스 접근 거부
    public void denyAccess(Long userId, Long serviceId) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<UserServiceAccess> accessList = userServiceAccessRepository.findByUser(user);
        UserServiceAccess userServiceAccess = null;
        for (int i = 0; i < accessList.size(); i++) {
            if (accessList.get(i).getServiceId()==serviceId){
                userServiceAccess=accessList.get(i);
                break;
            }
        }
        if (userServiceAccess != null){
            if (userServiceAccess.getServiceId().equals(serviceId)){
                userServiceAccess.setAccessGranted(false);
                userServiceAccess.setAccessStatus(AccessStatus.DENIED);
                userServiceAccess.setAccessEnded(LocalDateTime.now());
            }
        }
        else {
            userServiceAccess = UserServiceAccess.builder()
                    .user(user)
                    .serviceId(serviceId)
                    .accessStatus(AccessStatus.DENIED)
                    .accessGranted(false)
                    .accessEnded(LocalDateTime.now())
                    .build();
        }

        userServiceAccessRepository.save(userServiceAccess);
    }
}
