package com.arty.roadmapservice.dto.request.reflection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReflectionCreateDto {
    private Boolean learnedSomethingNew;
    private Integer satisfactionScore;
    private String whatWentWell;
    private String whatToImprove;
    private String additionalNotes;
    private Long milestoneId;
    private Long activityLogId;
}
