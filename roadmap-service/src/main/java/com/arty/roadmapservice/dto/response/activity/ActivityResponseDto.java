package com.arty.roadmapservice.dto.response.activity;


import com.arty.roadmapservice.dto.constants.enums.LogMode;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityResponseDto {
    private Long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime endTime;
    private Long roadmapId;
    private Long milestoneId;
    private LogMode logMode;
}
