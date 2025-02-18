package com.primitive.privacy_contest.Repository.UserActivity;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "User_Activity") // DB 테이블명 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPersonalInfo user; // FK (사용자)

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "activity_type", nullable = false, length = 100)
    private String activityType; // "로그인", "조회", "업로드" 등

    @Column(name = "metadata", nullable = false, length = 100)
    private String metadata; // "관련정보

    @Column(name = "activity_timestamp", nullable = false)
    private Timestamp activityTimestamp;
}
