package com.arty.roadmapservice.dto.request.milestone;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.models.annotations.spi.ColumnDetails;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
public class MilestoneCreateDto {
    private String milestoneName;
    private String milestoneDescription;
    private LocalDateTime milestoneCreation;
    private LocalDateTime milestoneEndDate;
    private Long attachedToRoadmapId;
}
