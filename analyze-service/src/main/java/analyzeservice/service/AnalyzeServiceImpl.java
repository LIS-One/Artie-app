package analyzeservice.service;

import analyzeservice.dto.ActivityDto;
import analyzeservice.dto.MilestoneDto;
import analyzeservice.dto.WrapMonthDto;
import analyzeservice.dto.constants.LogStatus;
import analyzeservice.feignclient.RoadmapClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyzeServiceImpl implements AnalyzeService {
    private final RoadmapClient roadmap;
    @Override
    public Integer totalHoursSpentOnRoadmap(Long roadmapId) {
        long totalMinutes;
        List<ActivityDto> activities = roadmap.getActivities(roadmapId);
        totalMinutes = activities.stream().filter(activity -> activity.getStatus() == LogStatus.COMPLETED && activity.getEndTime()!=null).mapToLong(activity -> (int) ChronoUnit.MINUTES.between(activity.getCreated(), activity.getEndTime())).sum();
        return (int)totalMinutes/60;
    }

    @Override
    public Integer compareProgress(Long roadmapId) {
        return 0;
    }

    @Override
    public List<MilestoneDto> hottestMilestones(Long roadmapId) {
        return List.of();
    }

    @Override
    public Integer streakCount(Long roadmapId) {
        return 0;
    }

    @Override
    public List<ActivityDto> recentLogs(Long roadmapId) {
        return roadmap.getRecentActivities(roadmapId);
    }

    @Override
    public Integer overallLogsCount(Long roadmapId) {
        return 0;
    }

    @Override
    public WrapMonthDto wrapMonth(String email) {
        return null;
    }
}
