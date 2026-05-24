package com.arty.roadmapservice.service.activity;


import com.arty.roadmapservice.dto.constants.enums.LogMode;
import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityTimedCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.entity.ActivityLog;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.exceptions.IllegalLogUpdateException;
import com.arty.roadmapservice.repository.ActivityLogRepository;
import com.arty.roadmapservice.repository.MilestoneRepository;
import com.arty.roadmapservice.repository.RoadmapRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.arty.roadmapservice.dto.constants.enums.LogMode.TIMED;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;
    private final ModelMapper modelMapper;
    private final RoadmapRepository roadmapRepository;
    private final MilestoneRepository milestoneRepository;

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
    public ActivityResponseDto updateActivityLog(Long id, ActivityUpdateDto activityUpdateDto) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(id);
        if (activityLog.isPresent()) {
            if (activityLog.get().getLogMode() == TIMED) {
                if (activityUpdateDto.getCreated() != null || activityUpdateDto.getEnded() != null) {
                    throw new IllegalLogUpdateException(
                            "Timestamps of TIMED logs are immutable"
                    );
                }

                activityLog.get().setName(activityUpdateDto.getName());
                activityLog.get().setUpdated(activityUpdateDto.getUpdated());
                activityLogRepository.save(activityLog.get());
                return toDto(activityLog.get());
            }
        }
        return activityLog.map(a -> {
            modelMapper.map(activityUpdateDto, a);
            activityLogRepository.save(a);
            return toDto(a);
        }).orElse(null);
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
    public ActivityResponseDto stopActivityLog(Long id) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(id);
        if (activityLog.isPresent()) {
            if (activityLog.get().getLogMode() == TIMED) {
                activityLog.get().setEnded(LocalDateTime.now());
                activityLogRepository.save(activityLog.get());
                return toDto(activityLog.get());
            }
        }
        throw new EntityNotFoundException("ActivityLog with id " + id + " not found");
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