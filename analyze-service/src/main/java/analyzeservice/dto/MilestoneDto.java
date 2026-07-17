package analyzeservice.dto;

import analyzeservice.dto.constants.MilestoneStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MilestoneDto {
   private String name;
    private String description;
    private Long milestoneProgress;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private MilestoneStatus status;
}
