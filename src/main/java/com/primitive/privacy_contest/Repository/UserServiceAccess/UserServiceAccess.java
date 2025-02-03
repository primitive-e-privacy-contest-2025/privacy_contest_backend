package com.primitive.privacy_contest.Repository.UserServiceAccess;

import com.primitive.privacy_contest.Repository.UserPersonalInfo.UserPersonalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "UserServiceAccess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserServiceAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPersonalInfo user;

    @Column(nullable = false)
    private Long serviceId;

    @Enumerated(EnumType.STRING)
    private AccessStatus accessStatus;

    private boolean accessGranted;

    private Timestamp accessStarted;
    private Timestamp accessEnded;
    private Timestamp lastAccessTime;
}
