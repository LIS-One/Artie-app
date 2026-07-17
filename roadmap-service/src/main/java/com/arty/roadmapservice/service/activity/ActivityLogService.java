package com.arty.roadmapservice.service.activity;

import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityTimedCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.entity.ActivityLog;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ActivityLogService {

    ActivityResponseDto createActivityLog(ActivityCreateDto activityCreateDto);

    ActivityResponseDto getActivityLog(Long activityLogId);

    ActivityResponseDto updateActivityLog(Long id,ActivityUpdateDto activityUpdateDto, String email) throws AccessDeniedException;

    Boolean deleteActivity(Long activityLogId);

    ActivityResponseDto startActivityLog(ActivityTimedCreateDto activityCreateDto);

    ActivityResponseDto stopActivityLog(Long id, String email) throws AccessDeniedException;

    List<ActivityResponseDto> getActivitiesInRoadmap(Long roadmapId);
    List<ActivityResponseDto> getRecentUserActivities(String email);

}
