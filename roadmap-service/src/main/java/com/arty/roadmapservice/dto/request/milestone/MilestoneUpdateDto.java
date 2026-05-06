package com.arty.roadmapservice.dto.request.milestone;

import com.arty.roadmapservice.dto.constants.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MilestoneUpdateDto {
    private String milestoneName;
    private String milestoneDescription;
    private LocalDateTime milestoneCreation;
    private LocalDateTime milestoneEndDate;
    private Status status;
}
