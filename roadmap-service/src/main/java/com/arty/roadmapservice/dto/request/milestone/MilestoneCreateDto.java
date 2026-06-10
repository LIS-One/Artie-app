package com.arty.roadmapservice.dto.request.milestone;


import lombok.Getter;
import lombok.Setter;





@Getter
@Setter
public class MilestoneCreateDto {
    private String name;
    private String description;
    private Long roadmapId;
}
