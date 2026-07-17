package com.arty.roadmapservice.repository;

import com.arty.roadmapservice.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {


    List<ActivityLog> findByRoadmap_Id(Long roadmapId);

    // путь: activityLog.roadmap.userProfile.id + фильтр по дате
    List<ActivityLog> findByRoadmap_UserProfile_IdAndCreatedAfter(Long userId, LocalDateTime created);

    List<ActivityLog> findByRoadmap_UserProfile_EmailAndCreatedAfter(String email, LocalDateTime created);

    List<ActivityLog> findTop12ByRoadmap_IdAndEndedIsNotNullOrderByEndedDesc(Long roadmapId);
}

