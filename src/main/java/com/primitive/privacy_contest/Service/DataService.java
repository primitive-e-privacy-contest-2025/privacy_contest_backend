package com.primitive.privacy_contest.Service;

import ch.qos.logback.core.OutputStreamAppender;
import com.primitive.privacy_contest.DTO.RegistCorporateUserDTO;
import com.primitive.privacy_contest.Repository.APICallLogs.APICallLogsRepository;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsers;
import com.primitive.privacy_contest.Repository.CorporateUsers.CorporateUsersRepository;
import com.primitive.privacy_contest.Repository.FileStorages.FileStorages;
import com.primitive.privacy_contest.Repository.FileStorages.FileStoragesRepository;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfoRepository;
import com.primitive.privacy_contest.Repository.UserServiceAccess.AccessStatus;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccess;
import com.primitive.privacy_contest.Repository.UserServiceAccess.UserServiceAccessRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DataService {
    @Autowired
    CorporateUsersRepository corporateUsersRepository;
    @Autowired
    UserPersonalInfoRepository userPersonalInfoRepository;
    @Autowired
    UserServiceAccessRepository userServiceAccessRepository;
    @Autowired
    APICallLogsRepository apiCallLogsRepository;
    @Autowired
    FileStoragesRepository fileStoragesRepository;
    @Autowired
    UserPersonalInfoRepository userRepository;

    public void createActivityData (long userId, long serviceId, String apikey, Byte[] csvdata){
        UserPersonalInfo user = userPersonalInfoRepository.findById(userId).get();

        ArrayList <FileStorages> temp = (ArrayList<FileStorages>) fileStoragesRepository.findByUser(user);
        ArrayList <FileStorages> list = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getService().getServiceId()==serviceId){
                list.add(temp.get(i));
            }
        }

        if(list.size()==0){
            FileStorages f =new FileStorages();
            fileStoragesRepository.save(f);
        }
        else if(list.size()==1){
            try {
                byte[] fileData = list.get(0).getFileData(); // 기존 파일 데이터 (BLOB)

                // csvData (Byte[]) → byte[] 변환 (필요하면 사용)
                byte[] csvBytes = new byte[csvdata.length];
                for (int i = 0; i < csvdata.length; i++) {
                    csvBytes[i] = csvdata[i]; // Byte -> byte 변환
                }

                // 기존 데이터 + 새 CSV 데이터 합치기
                byte[] mergedData = new byte[fileData.length + csvBytes.length];
                System.arraycopy(fileData, 0, mergedData, 0, fileData.length);
                System.arraycopy(csvBytes, 0, mergedData, fileData.length, csvBytes.length);

                // 새로운 파일 데이터를 저장 (DB 업데이트)
                FileStorages fileStorage = list.get(0);
                fileStorage.setFileData(mergedData); // 새 데이터로 업데이트
                fileStoragesRepository.save(fileStorage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void getActivityDataForUser(long userId, long serviceId){
        UserPersonalInfo user = userPersonalInfoRepository.findById(userId).get();
        ArrayList <FileStorages> temp = (ArrayList<FileStorages>) fileStoragesRepository.findByUser(user);
        ArrayList <FileStorages> list = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getService().getServiceId()==serviceId){
                list.add(temp.get(i));
            }
        }
    }
    public void getActivityDataForCorp(long userId, long serviceId,String apikey){
        UserPersonalInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<UserServiceAccess> accessService = userServiceAccessRepository.findByUser(user);

        for (int i = 0; i < accessService.size(); i++) {
            if(accessService.get(i).getServiceId()==serviceId){
                if (accessService.get(i).getAccessStatus().equals(AccessStatus.GRANTED)){

                }
            }
        }
    }

}
