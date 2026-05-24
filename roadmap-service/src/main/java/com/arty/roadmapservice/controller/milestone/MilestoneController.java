package com.arty.roadmapservice.controller.milestone;

import com.arty.roadmapservice.dto.constants.apipaths.ApiPaths;
import com.arty.roadmapservice.dto.request.milestone.MilestoneCreateDto;
import com.arty.roadmapservice.dto.request.milestone.MilestoneUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.service.milestone.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.MILESTONES)
public class MilestoneController {
    private final MilestoneService milestoneService;
    @PostMapping
    public MilestoneResponseDto createMilestone(@RequestBody MilestoneCreateDto milestone){
        return milestoneService.createMilestone(milestone);
    }
    @GetMapping(ApiPaths.BY_ID)
    public MilestoneResponseDto getMilestone(@PathVariable Long id){
        return milestoneService.getMilestone(id);
    }
    @PostMapping(ApiPaths.BY_ID)
    public MilestoneResponseDto updateMilestone(@PathVariable Long id, @RequestBody MilestoneUpdateDto milestone){
        return milestoneService.updateMilestone(id,milestone);
    }
    @DeleteMapping(ApiPaths.BY_ID)
    public Boolean deleteMilestone(@PathVariable Long id){
        return milestoneService.deleteMilestone(id);
    }

    @PatchMapping(ApiPaths.BY_ID)
    public MilestoneResponseDto completeMilestone(@PathVariable Long id){
        return milestoneService.completeMilestone(id);
    }

}
