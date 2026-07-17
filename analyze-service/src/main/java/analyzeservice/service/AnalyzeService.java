package analyzeservice.service;

import analyzeservice.dto.ActivityDto;
import analyzeservice.dto.MilestoneDto;
import analyzeservice.dto.WrapMonthDto;

import java.util.List;

public interface AnalyzeService {
     Integer totalHoursSpentOnRoadmap(Long roadmapId);

     Integer compareProgress(Long roadmapId);

     List<MilestoneDto> hottestMilestones(Long roadmapId);

     Integer streakCount(Long roadmapId);

     List<ActivityDto> recentLogs(Long roadmapId);

     Integer overallLogsCount(Long roadmapId);

     WrapMonthDto wrapMonth(String email);

}
