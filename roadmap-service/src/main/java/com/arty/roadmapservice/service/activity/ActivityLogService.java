package com.arty.roadmapservice.service.activity;

import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;

public interface ActivityLogService {

    ActivityResponseDto createActivityLog(ActivityCreateDto activityCreateDto);

    ActivityResponseDto getActivityLog(Long activityLogId);

    ActivityResponseDto updateActivityLog(Long id,ActivityUpdateDto activityUpdateDto);

    Boolean deleteActivity(Long activityLogId);

    ActivityResponseDto startActivityLog(String name);

    ActivityResponseDto stopActivityLog(Long id);

}
