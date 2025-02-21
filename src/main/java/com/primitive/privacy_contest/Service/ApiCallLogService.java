package com.primitive.privacy_contest.Service;

import com.primitive.privacy_contest.DTO.ApiCallLogDTO;
import com.primitive.privacy_contest.Repository.APICallLogs.APICallLogs;
import com.primitive.privacy_contest.Repository.APICallLogs.APICallLogsRepository;
import com.primitive.privacy_contest.Repository.Services.Services;
import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCallLogService {
    @Autowired
    APICallLogsRepository callLogsRepository;
    @Autowired
    UserService userService;
    public List<ApiCallLogDTO> getApiCallLog(Long userId) {
        UserPersonalInfo user = userService.getUserById(userId);
        List<APICallLogs> apiCallLogsList = callLogsRepository.findByUser(user);
        List<ApiCallLogDTO> apiCallLogDTOs=new ArrayList<>();
        for (int i = 0; i < apiCallLogsList.size(); i++) {
            apiCallLogDTOs.add(ApiCallLogDTO.builder()
                    .service(apiCallLogsList.get(i).getService().getServiceName())
                    .user(apiCallLogsList.get(i).getUser().getLoginId())
                    .endpoint(apiCallLogsList.get(i).getEndpoint())
                    .requestTime(apiCallLogsList.get(i).getRequestTime())
                    .responseStatus(apiCallLogsList.get(i).getResponseStatus())
                    .responseAbstract(apiCallLogsList.get(i).getResponseAbstract())
                    .build());
        }

        return apiCallLogDTOs;
    }
    public void putApiCallLog(Services services,UserPersonalInfo user, ApiCallLogDTO apiCallLogDTO, long time) {
        APICallLogs apiCallLogs
                = APICallLogs.builder()
                .service(services)
                .user(user)
                .endpoint(apiCallLogDTO.getEndpoint())
                .requestTime(apiCallLogDTO.getRequestTime())
                .responseStatus(apiCallLogDTO.getResponseStatus())
                .responseAbstract(apiCallLogDTO.getResponseAbstract())
                .duration(time)
                .build();
        callLogsRepository.save(apiCallLogs);
    }
}
