package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityTimedCreateDto {
    private String name;
    private Long roadmapId;
    private Long milestoneId;
}
