package com.arty.roadmapservice.dto.request.roadmap;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoadmapCreateDto {
    private String roadmapName;
    private String roadmapDescription;
    private LocalDateTime expirationDate;
}
