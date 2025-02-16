package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.ServiceRegistDTO;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Repository.Services.ServicesRepository;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import com.primitive.privacy_contest.Repository.UserServiceAccess.AccessStatus;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessService {
    @Autowired
    private UserServiceAccessRepository userServiceAccessRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private UserPersonalInfoRepository userPersonalInfoRepository;

    public long createRequest(String ApiKey, Long serviceId, Long user_id){
        if (servicesRepository.findById(serviceId).get().getApiKey()==ApiKey){
            UserPersonalInfo user = userPersonalInfoRepository.findById(user_id).get();
            UserServiceAccess userServiceAccess = new UserServiceAccess();
            userServiceAccess.setServiceId(serviceId);
            userServiceAccess.setUser(user);
            userServiceAccess.setAccessStatus(AccessStatus.PENDING);
            userServiceAccessRepository.save(userServiceAccess);
            return userServiceAccess.getAccessId();
        }else {
            return -1;
        }

    }
    public void grantRequest(Long user_id, String requestId){
        UserServiceAccess userServiceAccess = userServiceAccessRepository.findById(Long.getLong(requestId)).get();
        userServiceAccess.setAccessStatus(AccessStatus.GRANTED);
        userServiceAccessRepository.save(userServiceAccess);

    }
    public void rejectRequest(Long user_id, String requestId){     UserServiceAccess userServiceAccess = userServiceAccessRepository.findById(Long.getLong(requestId)).get();
        userServiceAccess.setAccessStatus(AccessStatus.DENIED);
        userServiceAccessRepository.save(userServiceAccess);
    }
}
