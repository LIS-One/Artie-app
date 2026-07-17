package com.arty.roadmapservice.service.roadmap;

import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;

public interface RoadmapService {
    RoadmapResponseDto createRoadmap(RoadmapCreateDto roadmapCreateDto, String email);

    RoadmapResponseDto getRoadmap(Long id);

    RoadmapResponseDto updateRoadmap(Long id, RoadmapUpdateDto roadmapUpdateDto);

    Boolean deleteRoadmap(Long id);

    RoadmapResponseDto completeRoadmap(Long id);

}
