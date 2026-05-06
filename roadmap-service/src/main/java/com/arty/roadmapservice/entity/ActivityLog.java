package com.arty.roadmapservice.entity;

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
    private int activityTime; // TODO change to actul time spent hours and shit
    @ManyToOne
    @JoinColumn(name = "attached_to_roadmap_id")
    private Roadmap attachedToRoadmap;
    @ManyToOne
    private Milestone attachedToMilestone;
}
