package com.arty.roadmapservice.dto.request.milestone;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class MilestoneCreateDto {
    private String milestoneName;
    private String milestoneDescription;
    private LocalDateTime milestoneCreation;
    private LocalDateTime milestoneEndDate;
    private Long attachedToRoadmapId;
}
