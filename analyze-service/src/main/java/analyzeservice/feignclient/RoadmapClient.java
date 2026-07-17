package analyzeservice.feignclient;

import analyzeservice.dto.ActivityDto;
import analyzeservice.dto.MilestoneDto;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "roadmap-service")
public interface RoadmapClient {

    @GetMapping("/api/roadmaps/{id}/milestones")
    List<MilestoneDto> getMilestones(@PathVariable Long id);
    @GetMapping("/api/roadmaps/{id}/activities")
    List<ActivityDto> getActivities(@PathVariable Long id);
    @GetMapping("/api/user/{id}/recent/activities")
    List<ActivityDto> getRecentActivities(@PathVariable Long id);
}
