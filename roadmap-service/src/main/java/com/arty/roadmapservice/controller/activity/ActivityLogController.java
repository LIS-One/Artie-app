package com.arty.roadmapservice.controller.activity;

import com.arty.roadmapservice.dto.constants.ApiPaths;
import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.service.activity.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
//TODO make all mappings paths with variables inside in ALL CONTROLLERS
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.PATH_ACTIVITY_LOG_BASE)
public class ActivityLogController {
    private final ActivityLogService activityLogService;

    @PostMapping
    public ActivityResponseDto createActivityLog(@RequestBody ActivityCreateDto activity) {
        return activityLogService.createActivityLog(activity);
    }
    @GetMapping
    public ActivityResponseDto getActivityLog(@PathVariable Long id){
        return activityLogService.getActivityLog(id);
    }
    @PostMapping
    public ActivityResponseDto updateActivityLog(@PathVariable Long id, @RequestBody ActivityUpdateDto activity){
        return activityLogService.updateActivityLog(id,activity);
    }
    @DeleteMapping
    public boolean deleteActivityLog(@PathVariable Long id){
        return activityLogService.deleteActivity(id);
    }
}
