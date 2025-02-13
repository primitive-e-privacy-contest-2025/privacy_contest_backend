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

    public Integer registCorporateUser(RegistCorporateUserDTO RegistCorporateUserDTO){
        try {
            CorporateUsers t=new CorporateUsers(RegistCorporateUserDTO);
            corporateUsersRepository.save(t);
            return t.getCorporateId();
        }catch (Exception e){
            return -1;
        }
    }
    public int loginCorporateUser(LoginDTO loginDTO){
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
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
    }


    public Integer patchCoporateUser(String corporateId, RegistCorporateUserDTO registCorporateUserDTO) {
        try {
            int id = Integer.getInteger(corporateId);
            Optional<CorporateUsers> optional = corporateUsersRepository.findById(id);
            optional.get().patch(registCorporateUserDTO);
            return 0;

        }catch (Exception e){
            return-1;
        }
    }

    public void deleteCoporateUser(String corporateId) {
        Optional<CorporateUsers> optional = corporateUsersRepository.findById(Integer.getInteger(corporateId));
        corporateUsersRepository.delete(optional.get());
    }
}
