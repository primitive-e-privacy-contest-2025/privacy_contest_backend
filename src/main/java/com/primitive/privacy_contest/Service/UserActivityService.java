package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.ActivityDTO;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsersRepository;
import com.primitive.privacy_contest.Repository.Services.ServicesRepository;
import com.primitive.privacy_contest.Repository.UserActivity.UserActivity;
import com.primitive.privacy_contest.Repository.UserActivity.UserActivityRepository;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserPersonalInfoRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private CorporateUsersRepository corporateUsersRepository;

    public UserActivityService(UserActivityRepository userActivityRepository, UserPersonalInfoRepository userRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
    }

    public List<UserActivity>  getUserActivityByCorp(Long userId,Long serviceId,String apiKey ){
        if (servicesRepository.findById(serviceId).get().getApiKey().equals(apiKey)){
            UserPersonalInfo user = userService.getUserById(userId);
            List<UserActivity> userActivities = userActivityRepository.findByUserAndServiceId(user, serviceId);
            return userActivities;
        }
        return null;
    }

    public void putUserActivity(Long userId,Long serviceId, ActivityDTO activityDTO) {
        try {
            System.out.println(activityDTO);
            UserActivity userActivity = UserActivity.builder()
                    .user(userService.getUserById(userId))
                    .serviceId(serviceId)
                    .activityType(activityDTO.getActivityType())
                    .metadata(activityDTO.getMetadata())
                    .activityTimestamp(LocalDateTime.now())
                    .build();
            userActivityRepository.save(userActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //사용자의 특정 서비스에서의 활동 이력 조회
    public List<UserActivity> getUserActivity(Long userId, Long serviceId) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userActivityRepository.findByUserAndServiceId(user, serviceId);
    }


    //특정 활동 이력 삭제
    public void deleteUserActivity(Long userId, Long serviceId, List<Long> activityIds) {
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<UserActivity> activities = userActivityRepository.findByUserAndServiceId(user, serviceId);
        activities.removeIf(activity -> !activityIds.contains(activity.getActivityId()));

        userActivityRepository.deleteAll(activities);
    }
}
