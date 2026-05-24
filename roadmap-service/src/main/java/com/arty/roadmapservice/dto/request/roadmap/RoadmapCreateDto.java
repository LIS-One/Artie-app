package com.arty.roadmapservice.dto.request.roadmap;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoadmapCreateDto {
    private String name;
    private String description;
    private LocalDateTime expirationDate;
    private Long userId;
}
