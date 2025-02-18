package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.RegistUserDTO;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import jakarta.validation.constraints.Null;
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

    public long createUser (RegistUserDTO registUserDTO){
        try {
            UserPersonalInfo newUser = new UserPersonalInfo();
            newUser.setLoginId(registUserDTO.getLoginId());
            newUser.setLoginPw(registUserDTO.getLoginPw());
            newUser.setFullName(registUserDTO.getFullName());
            newUser.setEmail(registUserDTO.getEmail());
            newUser.setPhoneNumber(registUserDTO.getPhoneNumber());
            newUser.setDateOfBirth(registUserDTO.getDateOfBirth());
            newUser.setAddress(registUserDTO.getAddress());
            userRepo.save(newUser);
            return newUser.getUserId();
        }catch (Exception e){
            return (long) -1;
        }

    }


    public UserPersonalInfo updateUser(Long userId, RegistUserDTO updatedUser) {
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
