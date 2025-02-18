package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPersonalInfoRepository userRepo;

    public UserPersonalInfo getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserPersonalInfo updateUser(Long userId, UserPersonalInfo updatedUser) {
        UserPersonalInfo user = getUserById(userId);
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAddress(updatedUser.getAddress());
        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
