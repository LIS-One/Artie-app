package com.arty.roadmapservice.entity;

import com.arty.roadmapservice.dto.constants.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String milestoneName;
    private String milestoneDescription;
    private LocalDateTime milestoneCreation;
    private LocalDateTime milestoneEndDate;
    @ManyToOne
    @JoinColumn(name = "attached_to_roadmap_id")
    private Roadmap attachedToRoadmap;
    @OneToMany(mappedBy = "attachedToMilestone", cascade = CascadeType.ALL)
    private List<ActivityLog> logsList;
    @Enumerated(EnumType.STRING)
    private boolean statusFinished;




}
