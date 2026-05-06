package com.arty.roadmapservice.controller.roadmap;

import com.arty.roadmapservice.dto.constants.ApiPaths;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.service.roadmap.RoadmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.PATH_ROADMAP_BASE)
public class RoadmapController {
    private final RoadmapService roadmapService;

    @PostMapping
    public RoadmapResponseDto createRoadmap(@RequestBody RoadmapCreateDto roadmap){
        return roadmapService.createRoadmap(roadmap);
    }
    @GetMapping
    public RoadmapResponseDto getRoadmap(@PathVariable Long id){
        return roadmapService.getRoadmap(id);
    }
    @PostMapping
    public RoadmapResponseDto updateRoadmap(@PathVariable Long id, @RequestBody RoadmapUpdateDto roadmap){
        return roadmapService.updateRoadmap(id, roadmap);
    }
    @DeleteMapping
    public Boolean deleteRoadmap(@PathVariable Long id){
        return roadmapService.deleteRoadmap(id);
    }
}
