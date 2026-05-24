package com.arty.roadmapservice.dto.response.milestone;


import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MilestoneResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private Long roadmapId;
    private RoadMilestoneStatus status;
}
