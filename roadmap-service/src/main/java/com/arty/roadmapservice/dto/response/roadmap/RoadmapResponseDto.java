package com.arty.roadmapservice.dto.response.roadmap;


import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class RoadmapResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private List<Long> milestoneListId;
    private Long userProfileId;
    private RoadMilestoneStatus status;
}
