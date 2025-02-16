package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.GetServiceDataDTO;
import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.DTO.RegistServiceDTO;
import com.primitive.privacy_contest.DTO.ServiceRegistDTO;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsersRepository;
import com.primitive.privacy_contest.Repository.Services.ServiceStatus;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Repository.Services.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesService {
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    CorporateUsersRepository corporateUsersRepository;

    public ServiceRegistDTO registerService(RegistServiceDTO registServiceDTO){
        try {
            long id = Long.getLong(registServiceDTO.getCorporateUserID());
            CorporateUsers corporateUser = corporateUsersRepository.findById(id).get();

            Services service =new Services();

            service.setServiceName(registServiceDTO.getServiceName());
            service.setDescription(registServiceDTO.getDescription());
            service.setStatus(ServiceStatus.ACTIVE);
            service.setCorporateUsers(corporateUser);
            String APIKey = new String("232321312312321312312");
            service.setApiKey(APIKey);
            servicesRepository.save(service);

            ServiceRegistDTO serviceRegistDTO = new ServiceRegistDTO(service.getServiceId().toString(),service.getApiKey());
            return serviceRegistDTO;
        }catch (Exception e){
            ServiceRegistDTO serviceRegistDTO = new ServiceRegistDTO(null,null);
            return serviceRegistDTO;
        }


    }

    public List<Services> getService(Long corporateId){
        try {
            CorporateUsers corporateUser = corporateUsersRepository.findById(corporateId).get();
            List<Services> list = servicesRepository.findByCorporateUsers(corporateUser);
            return list;
        }catch (Exception e){
            List<Services> list= new ArrayList<>();
        }
        return List.of();
    }

    public Integer patchService(String ServiceID, GetServiceDataDTO dataDTO) {
        try {
            long id = Long.getLong(ServiceID);
            Services service = servicesRepository.findById(id).get();
            service.setServiceName(dataDTO.getServiceName());
            service.setDescription(dataDTO.getDescription());
            service.setStatus(dataDTO.getStatus());
        }catch (Exception e){
            return-1;
        }
        return 0;
    }

    public void deleteService(String ServiceId) {
        Optional<Services> optional = servicesRepository.findById(Long.getLong(ServiceId));
        servicesRepository.delete(optional.get());
    }
}
