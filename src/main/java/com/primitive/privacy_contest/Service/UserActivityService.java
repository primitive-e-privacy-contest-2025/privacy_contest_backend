package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.Repository.UserActivity.UserActivity;
import com.primitive.privacy_contest.Repository.UserActivity.UserActivityRepository;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserPersonalInfoRepository userRepository;

    public UserActivityService(UserActivityRepository userActivityRepository, UserPersonalInfoRepository userRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
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
