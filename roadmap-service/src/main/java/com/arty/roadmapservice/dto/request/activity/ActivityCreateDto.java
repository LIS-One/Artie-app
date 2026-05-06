package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityCreateDto {
    private String activityName;
    private LocalDateTime activityCreated;
    private LocalDateTime activityUpdated;
    private int activityTime;
    private Long attachedToRoadmapId;
    private Long attachedToMilestoneId;
}
