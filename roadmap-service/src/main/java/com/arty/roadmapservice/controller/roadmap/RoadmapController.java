package com.arty.roadmapservice.controller.roadmap;

import com.arty.roadmapservice.dto.constants.apipaths.ApiPaths;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.service.roadmap.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ROADMAPS)
public class RoadmapController {
    private final RoadmapService roadmapService;

    @PostMapping
    public RoadmapResponseDto createRoadmap(@RequestBody RoadmapCreateDto roadmap){
        return roadmapService.createRoadmap(roadmap);
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
}
