package com.arty.roadmapservice.dto.request.milestone;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MilestoneUpdateDto {
    private String name;
    private String description;
    private LocalDateTime expirationDate;
}
