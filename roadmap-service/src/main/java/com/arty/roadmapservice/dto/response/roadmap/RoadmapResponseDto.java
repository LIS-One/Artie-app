package com.arty.roadmapservice.dto.response.roadmap;


import lombok.Getter;
import lombok.Setter;

import com.arty.roadmapservice.dto.constants.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class RoadmapResponseDto {
    private Long id;
    private String roadmapName;
    private String roadmapDescription;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private List<Long> milestoneListId;
    private Long userId;
    private Status finished;
}
