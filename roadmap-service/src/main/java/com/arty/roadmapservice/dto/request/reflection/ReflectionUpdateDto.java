package com.arty.roadmapservice.dto.request.reflection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReflectionUpdateDto {
    private Boolean learnedSomethingNew;
    private Integer satisfactionScore;
    private String whatWentWell;
    private String whatToImprove;
    private String additionalNotes;
}
