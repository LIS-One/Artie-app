package com.arty.roadmapservice.controller.roadmap;

import com.arty.roadmapservice.dto.constants.apipaths.ApiPaths;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.service.activity.ActivityLogService;
import com.arty.roadmapservice.service.milestone.MilestoneService;
import com.arty.roadmapservice.service.roadmap.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ROADMAPS)
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final MilestoneService milestoneService;
    private final ActivityLogService activityLogService;

    @PostMapping
    public RoadmapResponseDto createRoadmap(@RequestBody RoadmapCreateDto roadmap, Principal principal){
        return roadmapService.createRoadmap(roadmap, principal.getName());
    }
    @GetMapping(ApiPaths.BY_ID)
    public RoadmapResponseDto getRoadmap(@PathVariable Long id){
        return roadmapService.getRoadmap(id);
    }
    @PostMapping(ApiPaths.BY_ID)
    public RoadmapResponseDto updateRoadmap(@PathVariable Long id, @RequestBody RoadmapUpdateDto roadmap){
        return roadmapService.updateRoadmap(id, roadmap);
    }
    @DeleteMapping(ApiPaths.BY_ID)
    public Boolean deleteRoadmap(@PathVariable Long id){
        return roadmapService.deleteRoadmap(id);
    }
    @PatchMapping(ApiPaths.BY_ID)
    public RoadmapResponseDto completeRoadmap(@PathVariable Long id){
        return roadmapService.completeRoadmap(id);
    }
    @GetMapping(ApiPaths.BY_ID + ApiPaths.MILESTONES_IN_ROADMAP)
    public List<MilestoneResponseDto> getMilestonesInRoadmap(@PathVariable Long id){
        return milestoneService.getMilestonesInRoadmap(id);}
    @GetMapping(ApiPaths.BY_ID + ApiPaths.ACTIVITIES_IN_ROADMAP)
    public List<ActivityResponseDto> getActivitiesInRoadmap(@PathVariable Long id){
        return activityLogService.getActivitiesInRoadmap(id);
    }
}
