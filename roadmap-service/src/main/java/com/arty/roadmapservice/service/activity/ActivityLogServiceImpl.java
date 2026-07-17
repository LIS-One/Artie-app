package com.arty.roadmapservice.service.activity;


import com.arty.roadmapservice.dto.constants.enums.LogMode;
import com.arty.roadmapservice.dto.constants.enums.LogStatus;
import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityTimedCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.entity.ActivityLog;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.entity.UserProfile;
import com.arty.roadmapservice.exceptions.IllegalLogUpdateException;
import com.arty.roadmapservice.repository.ActivityLogRepository;
import com.arty.roadmapservice.repository.MilestoneRepository;
import com.arty.roadmapservice.repository.RoadmapRepository;
import com.arty.roadmapservice.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.arty.roadmapservice.dto.constants.enums.LogMode.MANUAL;
import static com.arty.roadmapservice.dto.constants.enums.LogMode.TIMED;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;
    private final ModelMapper modelMapper;
    private final RoadmapRepository roadmapRepository;
    private final MilestoneRepository milestoneRepository;
    private final UserService userService;

    @Transactional
    @Override
    public ActivityResponseDto createActivityLog(ActivityCreateDto createActivity) {
        ActivityLog activity = modelMapper.map(createActivity, ActivityLog.class);

        Roadmap roadmap = roadmapRepository.findById(createActivity.getRoadmapId())
                .orElseThrow(EntityNotFoundException::new);
        activity.setRoadmap(roadmap);

        Milestone milestone = milestoneRepository.findById(createActivity.getMilestoneId())
                .orElseThrow(EntityNotFoundException::new);
        activity.setMilestone(milestone);

        activity.setLogMode(LogMode.MANUAL);
        activityLogRepository.save(activity);
        return toDto(activity);
    }

    @Transactional(readOnly = true)
    @Override
    public ActivityResponseDto getActivityLog(Long activityLogId) {
        return activityLogRepository.findById(activityLogId)
                .map(this::toDto)
                .orElse(null);
    }

    @Transactional
    @Override
    public ActivityResponseDto updateActivityLog(Long id, ActivityUpdateDto activityUpdateDto, String email) throws AccessDeniedException {
        ActivityLog activityLog = activityLogRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (!activityLog.getRoadmap().getUserProfile().getEmail().equals(email)) {
            throw new AccessDeniedException("Not your log");
        }
            if (activityLog.getLogMode() == TIMED) {
                if (activityUpdateDto.getCreated() != null || activityUpdateDto.getEnded() != null) {
                    throw new IllegalLogUpdateException(
                            "Timestamps of TIMED logs are immutable"
                    );
                }
                if (activityUpdateDto.getName()!=null){
                    activityLog.setName(activityUpdateDto.getName());

                }
            } else if (activityLog.getLogMode() == MANUAL) {

                }
                if(activityUpdateDto.getName()!=null)
                    activityLog.setName(activityUpdateDto.getName());
                LocalDateTime created = activityUpdateDto.getCreated()!=null?activityUpdateDto.getCreated():activityLog.getCreated();
                if (activityUpdateDto.getEnded() != null && ChronoUnit.MINUTES.between(created, activityUpdateDto.getEnded()) > 720) {
                    throw new IllegalLogUpdateException("Log cannot be over 12 hours");
                }
                if (activityUpdateDto.getCreated() != null) {
                    activityLog.setCreated(activityUpdateDto.getCreated());
                }
                if (activityUpdateDto.getEnded() != null) {

                    activityLog.setEnded(activityUpdateDto.getEnded());
                    activityLog.setLogStatus(LogStatus.COMPLETED);
                    LocalDate ended = activityLog.getEnded().toLocalDate();

                    updateStreak(activityLog.getRoadmap().getUserProfile(), ended);


                }
        activityLogRepository.save(activityLog);
        return toDto(activityLog);
    }

    @Transactional
    @Override
    public Boolean deleteActivity(Long activityLogId) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(activityLogId);
        activityLog.ifPresent(activityLogRepository::delete);
        return !activityLogRepository.existsById(activityLogId);
    }

    @Transactional
    @Override
    public ActivityResponseDto startActivityLog(ActivityTimedCreateDto createActivity) {
        ActivityLog timedLog = new ActivityLog();
        timedLog.setLogMode(TIMED);
        timedLog.setName(createActivity.getName());
        timedLog.setCreated(LocalDateTime.now());
        Roadmap roadmap = roadmapRepository.findById(createActivity.getRoadmapId())
                .orElseThrow(EntityNotFoundException::new);
        timedLog.setRoadmap(roadmap);

        Milestone milestone = milestoneRepository.findById(createActivity.getMilestoneId())
                .orElseThrow(EntityNotFoundException::new);
        timedLog.setMilestone(milestone);
        activityLogRepository.save(timedLog);
        return toDto(timedLog);
    }




    @Transactional
    @Override
    public ActivityResponseDto stopActivityLog(Long id, String email) throws AccessDeniedException {

        ActivityLog activityLog = activityLogRepository.findById(id).orElseThrow(NoSuchElementException::new);

            if (!activityLog.getRoadmap().getUserProfile().getEmail().equals(email))
                throw new AccessDeniedException("Not your log");
            if (activityLog.getLogMode() == TIMED) {
                if(activityLog.getLogStatus().equals(LogStatus.COMPLETED))
                    throw new IllegalLogUpdateException("Log is already completed");
                activityLog.setEnded(LocalDateTime.now());
                activityLog.setLogStatus(LogStatus.COMPLETED);
                LocalDate ended = activityLog.getEnded().toLocalDate();
                updateStreak(activityLog.getRoadmap().getUserProfile(),ended);
                activityLogRepository.save(activityLog);
                return toDto(activityLog);
            }
            throw new EntityNotFoundException("ActivityLog with id " + id + " not found");

    }

    private void updateStreak(UserProfile user,  LocalDate ended) {
        LocalDate now = LocalDate.now();
        LocalDate lastLog = user.getLastLogDate();
        int streak = user.getStreak();
            if (ended.equals(now)) {
                if(now.equals(lastLog)) {

                }else if(now.minusDays(1).equals(lastLog)) {
                    user.setStreak(streak + 1);
                }else{
                    user.setStreak(1);
                }
                user.setLastLogDate(now);
            }


    }


    @Transactional(readOnly = true)
    @Override
    public List<ActivityResponseDto> getActivitiesInRoadmap(Long roadmapId) {
        return activityLogRepository.findByRoadmap_Id(roadmapId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ActivityResponseDto> getRecentUserActivities(String email) {

        LocalDateTime after = LocalDate.now().minusDays(60).atStartOfDay();
        return activityLogRepository.findByRoadmap_UserProfile_EmailAndCreatedAfter(email, after)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ActivityResponseDto toDto(ActivityLog a) {
        if (a == null) return null;
        ActivityResponseDto dto = new ActivityResponseDto();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setCreated(a.getCreated());
        dto.setUpdated(a.getUpdated());
        dto.setEndTime(a.getEnded());
        dto.setLogMode(a.getLogMode());
        dto.setRoadmapId(a.getRoadmap() != null ? a.getRoadmap().getId() : null);
        dto.setMilestoneId(a.getMilestone() != null ? a.getMilestone().getId() : null);
        return dto;
    }
}