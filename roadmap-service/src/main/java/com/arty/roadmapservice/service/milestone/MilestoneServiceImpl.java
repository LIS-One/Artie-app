package com.arty.roadmapservice.service.milestone;

import com.arty.roadmapservice.dto.request.milestone.MilestoneCreateDto;
import com.arty.roadmapservice.dto.request.milestone.MilestoneUpdateDto;
import common.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService{
    private final MilestoneRepository milestoneRepository;
    private final ModelMapper modelMapper;

    @Override
    public MilestoneResponseDto createMilestone(MilestoneCreateDto milestoneCreateDto) {
        Milestone milestone = modelMapper.map(milestoneCreateDto,Milestone.class);
        milestoneRepository.save(milestone);
        return modelMapper.map(milestone,MilestoneResponseDto.class);
    }

    @Override
    public MilestoneResponseDto getMilestone(Long id) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);
        return milestone.map(m->modelMapper.map(m,MilestoneResponseDto.class)).orElse(null);
    }

    @Override
    public MilestoneResponseDto updateMilestone(Long id, MilestoneUpdateDto milestoneUpdateDto) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);

        return milestone.map(m->{
            modelMapper.map(milestoneUpdateDto,m);
            milestoneRepository.save(m);
            return modelMapper.map(m,MilestoneResponseDto.class);
        }).orElse(null);
    }

    @Override
    public Boolean deleteMilestone(Long id) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);
        milestone.ifPresent(milestoneRepository::delete);
        return !milestoneRepository.existsById(id);
    }
}
