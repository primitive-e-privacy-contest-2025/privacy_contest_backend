package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.LoginDTO;
import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorporateService {
    @Autowired
    CorporateUsersRepository corporateUsersRepository;

    public Long registCorporateUser(RegistCorporateUserDTO RegistCorporateUserDTO){
        try {
            CorporateUsers t=new CorporateUsers(RegistCorporateUserDTO);

            System.out.println(t.getCorporateId());
            System.out.println(t.getContactEmail());
            System.out.println(t.getCompanyName());
            System.out.println(t.getManagerEmail());
            System.out.println(t.getManagerName());
            System.out.println(t.getManagerEmail());
            System.out.println(t.getManagerPhone());
            System.out.println(t.getContactPhone());
            System.out.println(t.getContactPhone());

            corporateUsersRepository.save(t);
            return t.getCorporateId();
        }catch (Exception e){
            return (long) -1;
        }
    }
    public Long loginCorporateUser(LoginDTO loginDTO){
        try {
            List<CorporateUsers> CorporateUsers = corporateUsersRepository.findByLoginId(loginDTO.getLoginId());
            boolean flag=false;
            for(int i=0;i<CorporateUsers.size();i++){
                flag=true;
            }//flag가 true라면 존재하는 유저
            if(flag&&CorporateUsers.get(0).getLoginPw().equals(loginDTO.getLoginPw())){
                return CorporateUsers.get(0).getCorporateId();
            }
            else {
                return (long)-1;
            }
        }catch (Exception e){
            return (long)-1;
        }
    }


    public Integer patchCoporateUser(String corporateId, RegistCorporateUserDTO registCorporateUserDTO) {
        try {
            long id = Long.parseLong(corporateId);
            CorporateUsers corporateUsers = corporateUsersRepository.findById(id).get();
            corporateUsers.patch(registCorporateUserDTO);
            corporateUsersRepository.save(corporateUsers);
            return 0;

        }catch (Exception e){
            return-1;
        }
    }

    public long deleteCoporateUser(String corporateId) {
        try {
            long id = Long.parseLong(corporateId);
            Optional<CorporateUsers> optional = corporateUsersRepository.findById(Long.parseLong(corporateId));
            corporateUsersRepository.delete(optional.get());
            return id;
        }catch (Exception e){
            return-1;
        }

    }
}
