package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.LoginDTO;
import com.primitive.privacy_contest.DTO.RegistUserDTO;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            userRepo.save(newUser);
            return newUser.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            return -1L;
        }

    }
    public Long loginUser(LoginDTO loginDTO){
        try {
            Optional<UserPersonalInfo> users = userRepo.findByLoginId(loginDTO.getLoginId());
            boolean flag=users.isPresent();
            //flag가 true라면 존재하는 유저
            if(flag&&users.get().getLoginPw().equals(loginDTO.getLoginPw())){
                return users.get().getUserId();
            }
            else {
                return -1L;
            }
        }catch (Exception e){
            return -1L;
        }
    }

    public UserPersonalInfo updateUser(Long userId, RegistUserDTO updatedUser) {
        UserPersonalInfo user = getUserById(userId);
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
