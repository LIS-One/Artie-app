package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityTimedCreateDto {
    private String name;
    private Long roadmapId;
    private Long milestoneId;
}
