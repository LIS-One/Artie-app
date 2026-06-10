package com.arty.roadmapservice.entity;

import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Roadmap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL)
    private List<Milestone> milestones = new ArrayList<>();

    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL)
    private List<ActivityLog> logs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    private RoadMilestoneStatus status = RoadMilestoneStatus.IN_PROGRESS;


    public void markCompleted() {
        if(status.equals(RoadMilestoneStatus.COMPLETED)) {
            this.endDate = LocalDateTime.now();
        }
    }

}
