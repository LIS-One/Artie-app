package com.arty.roadmapservice.dto.response.activity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityResponseDto {
    private Long id;
    private String activityName;
    private LocalDateTime activityCreated;
    private LocalDateTime activityUpdated;
    private int activityTime;
    private Long attachedToRoadmapId;
    private Long attachedToMilestoneId;
}
