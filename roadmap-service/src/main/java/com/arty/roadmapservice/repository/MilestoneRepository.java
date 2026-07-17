package com.arty.roadmapservice.repository;

import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> getMilestonesByRoadmap_Id(Long roadmapId);
}
