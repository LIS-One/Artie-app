package com.arty.roadmapservice.service.milestone;

import com.arty.roadmapservice.dto.request.milestone.MilestoneCreateDto;
import com.arty.roadmapservice.dto.request.milestone.MilestoneUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;

public interface MilestoneService {
    MilestoneResponseDto createMilestone(MilestoneCreateDto milestoneCreateDto);

    MilestoneResponseDto getMilestone(Long id);

    MilestoneResponseDto updateMilestone(Long id,MilestoneUpdateDto milestoneUpdateDto);

    Boolean deleteMilestone(Long id);

    MilestoneResponseDto completeMilestone(Long id);
}
