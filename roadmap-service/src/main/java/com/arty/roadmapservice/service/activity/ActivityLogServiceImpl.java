package com.arty.roadmapservice.service.activity;

import com.arty.roadmapservice.dto.request.activity.ActivityCreateDto;
import com.arty.roadmapservice.dto.request.activity.ActivityUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.entity.ActivityLog;
import com.arty.roadmapservice.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService{
    private final ActivityLogRepository activityLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public ActivityResponseDto createActivityLog(ActivityCreateDto activityCreateDto) {
        ActivityLog activityLog = modelMapper.map(activityCreateDto, ActivityLog.class);
        activityLogRepository.save(activityLog);

        return modelMapper.map(activityLog, ActivityResponseDto.class);
    }

    @Override
    public ActivityResponseDto getActivityLog(Long activityLogId) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(activityLogId);
        return activityLog.map(a->modelMapper.map(a, ActivityResponseDto.class)).orElse(null);
    }

    @Override
    public ActivityResponseDto updateActivityLog(Long id, ActivityUpdateDto activityUpdateDto) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(id);
        return activityLog.map(a->{
            modelMapper.map(activityUpdateDto,a);
            activityLogRepository.save(a);
            return modelMapper.map(a,ActivityResponseDto.class);
        }).orElse(null);
    }

    @Override
    public Boolean deleteActivity(Long activityLogId) {
        Optional<ActivityLog> activityLog = activityLogRepository.findById(activityLogId);
        activityLog.ifPresent(activityLogRepository::delete);
        return !activityLogRepository.existsById(activityLogId);
    }
}
