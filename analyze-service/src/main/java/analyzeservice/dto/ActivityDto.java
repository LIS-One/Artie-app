package analyzeservice.dto;

import analyzeservice.dto.constants.LogStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ActivityDto {
   private String name;
    private String activityDescription;
    private LocalDateTime created;
    private LocalDateTime endTime;
    private LogStatus status;

}
