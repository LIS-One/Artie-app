package com.arty.roadmapservice.repository;

import com.arty.roadmapservice.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
