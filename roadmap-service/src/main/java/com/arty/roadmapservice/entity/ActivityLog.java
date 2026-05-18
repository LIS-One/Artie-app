package com.arty.roadmapservice.entity;

import com.arty.roadmapservice.dto.constants.enums.LogMode;
import com.arty.roadmapservice.dto.constants.enums.LogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityName;
    private LocalDateTime activityCreated;
    private LocalDateTime activityUpdated;
    private LocalDateTime activityEnded;
    @ManyToOne
    @JoinColumn(name = "attached_to_roadmap_id")
    private Roadmap attachedToRoadmap;
    @ManyToOne
    private Milestone attachedToMilestone;
    private LogStatus logStatus;
    private LogMode logMode;
}
