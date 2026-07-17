package analyzeservice.controller;

import analyzeservice.dto.ActivityDto;
import analyzeservice.dto.MilestoneDto;
import analyzeservice.dto.WrapMonthDto;
import analyzeservice.service.AnalyzeService;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AnalyzeController {
    private final AnalyzeService analyzeService;
    @GetMapping
    public Integer totalHoursSpent(Long roadmapId) {
        return analyzeService.totalHoursSpentOnRoadmap(roadmapId);
    }
    @GetMapping
    public Integer compareProgress(Long roadmapId) {
        return analyzeService.compareProgress(roadmapId);
    }
    @GetMapping
    public List<MilestoneDto> hottestMilestones(Long roadmapId) {
        return analyzeService.hottestMilestones(roadmapId);
    }
    @GetMapping
    public Integer streakCount(Long roadmapId) {
        return analyzeService.streakCount(roadmapId);
    }
    @GetMapping
    public List<ActivityDto> recentLogs(Long roadmapId) {
        return analyzeService.recentLogs(roadmapId);
    }
    @GetMapping
    public Integer overallLogsCount(Long roadmapId) {
        return analyzeService.overallLogsCount(roadmapId);
    }
    @GetMapping
    public WrapMonthDto wrapMonth(Principal principal){
        return analyzeService.wrapMonth(principal.getName());
    }
}
