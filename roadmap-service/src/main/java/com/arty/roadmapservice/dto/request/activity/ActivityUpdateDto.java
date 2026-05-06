package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityUpdateDto {
    private String activityName;
    private LocalDateTime activityUpdated;
    private int activityTime;
}
