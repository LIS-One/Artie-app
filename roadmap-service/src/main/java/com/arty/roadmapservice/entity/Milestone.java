package com.arty.roadmapservice.entity;

import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL)
    private List<ActivityLog> logs =  new ArrayList<>();

    private RoadMilestoneStatus status = RoadMilestoneStatus.IN_PROGRESS;


    public void markCompleted() {
        if(status.equals(RoadMilestoneStatus.COMPLETED)) {
            this.endDate = LocalDateTime.now();
        }
    }






}
