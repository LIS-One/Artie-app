package com.arty.roadmapservice.dto.request.roadmap;

import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoadmapUpdateDto {
    private String roadmapName;
    private String roadmapDescription;
    private LocalDateTime expirationDate;
    private RoadMilestoneStatus status;
}
