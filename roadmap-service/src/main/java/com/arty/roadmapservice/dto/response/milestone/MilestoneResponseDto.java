package com.arty.roadmapservice.dto.response.milestone;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import com.arty.roadmapservice.dto.constants.enums.Status;

@Getter
@Setter
public class MilestoneResponseDto {
    private Long id;
    private String milestoneName;
    private String milestoneDescription;
    private LocalDateTime milestoneCreation;
    private LocalDateTime milestoneEndDate;
    private Long attachedToRoadmapId;
    private Status finished;
}
