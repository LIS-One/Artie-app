package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityCreateDto {
    private String name;
    private LocalDateTime created;
    private LocalDateTime ended;
    private Long roadmapId;
    private Long milestoneId;
}
