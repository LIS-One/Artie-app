package com.arty.roadmapservice.service.activity;


import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.entity.ActivityLog;
import com.arty.roadmapservice.exceptions.IllegalLogUpdateException;
import com.arty.roadmapservice.repository.ActivityLogRepository;
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
public class ActivityLogServiceImpl implements ActivityLogService{
    private final ActivityLogRepository activityLogRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public ActivityResponseDto createActivityLog(ActivityCreateDto activityCreateDto) {
        ActivityLog activityLog = modelMapper.map(activityCreateDto, ActivityLog.class);
        activityLogRepository.save(activityLog);

        return modelMapper.map(activityLog, ActivityResponseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public ActivityResponseDto getActivityLog(Long activityLogId) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(activityLogId);
        return activityLog.map(a->modelMapper.map(a, ActivityResponseDto.class)).orElse(null);
    }
    @Transactional
    @Override
    public ActivityResponseDto updateActivityLog(Long id, ActivityUpdateDto activityUpdateDto) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(id);
        if (activityLog.isPresent()) {
            if (activityLog.get().getLogMode() == TIMED) {
                if (activityUpdateDto.getActivityCreated() != null || activityUpdateDto.getActivityEnded() != null) {
                    throw new IllegalLogUpdateException(
                            "Timestamps of TIMED logs are immutable"
                    );
                }

                activityLog.get().setActivityName(activityUpdateDto.getActivityName());
                activityLog.get().setActivityUpdated(activityUpdateDto.getActivityUpdated());
                activityLogRepository.save(activityLog.get());
                return modelMapper.map(activityLog.get(), ActivityResponseDto.class);
            }
        }
        return activityLog.map(a -> {
            modelMapper.map(activityUpdateDto, a);
            activityLogRepository.save(a);
            return modelMapper.map(a, ActivityResponseDto.class);
        }).orElse(null);
    }
    @Transactional
    @Override
    public Boolean deleteActivity (Long activityLogId){
        Optional<ActivityLog> activityLog = activityLogRepository.findById(activityLogId);
        activityLog.ifPresent(activityLogRepository::delete);
        return !activityLogRepository.existsById(activityLogId);
    }
    @Transactional
    @Override
    public ActivityResponseDto startActivityLog(String name) {
        ActivityLog timedLog = new ActivityLog();
        timedLog.setLogMode(TIMED);
        timedLog.setActivityName(name);
        timedLog.setActivityCreated(LocalDateTime.now());
        activityLogRepository.save(timedLog);
        return modelMapper.map(timedLog, ActivityResponseDto.class);
    }

    @Transactional
    @Override
    public ActivityResponseDto stopActivityLog(Long id) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(id);
        if (activityLog.isPresent()) {
            if (activityLog.get().getLogMode() == TIMED) {
                activityLog.get().setActivityEnded(LocalDateTime.now());
                activityLogRepository.save(activityLog.get());
                return modelMapper.map(activityLog.get(), ActivityResponseDto.class);
            }


        }
        throw new EntityNotFoundException("ActivityLog with id " + id + " not found");
    }
}
