package com.arty.roadmapservice.dto.request.milestone;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
public class MilestoneCreateDto {
    private String name;
    private String description;
    private Long roadmapId;
}
