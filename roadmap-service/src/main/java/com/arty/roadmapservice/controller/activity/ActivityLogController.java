package com.arty.roadmapservice.controller.activity;

import com.arty.roadmapservice.dto.constants.apipaths.ApiPaths;
import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityTimedCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.service.activity.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import static com.arty.roadmapservice.dto.constants.apipaths.ApiPaths.BY_ID;

//TODO make all mappings paths with variables inside in ALL CONTROLLERS
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ACTIVITY)
public class ActivityLogController {



    private final ActivityLogService activityLogService;

    @PostMapping
    public ActivityResponseDto createActivityLog(@RequestBody ActivityCreateDto activity) {
        return activityLogService.createActivityLog(activity);
    }

    @GetMapping(BY_ID)
    public ActivityResponseDto getActivityLog(@PathVariable Long id) {
        return activityLogService.getActivityLog(id);
    }


    @PutMapping(BY_ID)
    public ActivityResponseDto updateActivityLog(@PathVariable Long id, @RequestBody ActivityUpdateDto activity, Principal principal) throws AccessDeniedException {
        return activityLogService.updateActivityLog(id, activity, principal.getName());
    }

    @DeleteMapping(BY_ID)
    public boolean deleteActivityLog(@PathVariable Long id) {
        return activityLogService.deleteActivity(id);
    }

    @PostMapping("/timer/start")
    public ActivityResponseDto startActivityLog(@RequestBody ActivityTimedCreateDto createActivity) {
        return activityLogService.startActivityLog(createActivity);
    }
    @PatchMapping("/timer/stop/{id}")
    public ActivityResponseDto stopActivityLog(@PathVariable Long id, Principal principal) throws AccessDeniedException {
        return activityLogService.stopActivityLog(id, principal.getName());
    }
}

