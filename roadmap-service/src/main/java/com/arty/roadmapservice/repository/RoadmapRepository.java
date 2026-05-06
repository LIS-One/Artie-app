package com.arty.roadmapservice.repository;

import com.arty.roadmapservice.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
}
