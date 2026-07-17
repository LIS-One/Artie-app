package com.arty.roadmapservice.service.milestone;

import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import com.arty.roadmapservice.dto.request.milestone.MilestoneCreateDto;
import com.arty.roadmapservice.dto.request.milestone.MilestoneUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.repository.MilestoneRepository;

import com.arty.roadmapservice.repository.RoadmapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService{
    private final MilestoneRepository milestoneRepository;
    private final ModelMapper modelMapper;
    private final RoadmapRepository roadmapRepository;

    @Transactional
    @Override
    public MilestoneResponseDto createMilestone(MilestoneCreateDto milestoneCreateDto) {
        Milestone milestone = modelMapper.map(milestoneCreateDto, Milestone.class);
        milestone.setRoadmap(roadmapRepository.findById(milestoneCreateDto.getRoadmapId())
                .orElseThrow(EntityNotFoundException::new));
        milestoneRepository.save(milestone);
        return toDto(milestone);
    }

    @Transactional(readOnly = true)
    @Override
    public MilestoneResponseDto getMilestone(Long id) {
        return milestoneRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Transactional
    @Override
    public MilestoneResponseDto updateMilestone(Long id, MilestoneUpdateDto milestoneUpdateDto) {
        return milestoneRepository.findById(id).map(m -> {
            modelMapper.map(milestoneUpdateDto, m);
            milestoneRepository.save(m);
            return toDto(m);
        }).orElse(null);
    }


    @Transactional
    @Override
    public Boolean deleteMilestone(Long id) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);
        milestone.ifPresent(milestoneRepository::delete);
        return !milestoneRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MilestoneResponseDto> getMilestonesInRoadmap(Long id) {
        return milestoneRepository.getMilestonesByRoadmap_Id(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    @Override
    public MilestoneResponseDto completeMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        milestone.setStatus(RoadMilestoneStatus.COMPLETED);
        milestone.markCompleted();
        milestoneRepository.save(milestone);
        return toDto(milestone);
    }

    private MilestoneResponseDto toDto(Milestone m) {
        if (m == null) return null;
        MilestoneResponseDto dto = new MilestoneResponseDto();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setDescription(m.getDescription());
        dto.setCreationDate(m.getCreationDate());
        dto.setEndDate(m.getEndDate());
        dto.setStatus(m.getStatus());
        dto.setRoadmapId(m.getRoadmap() != null ? m.getRoadmap().getId() : null);
        return dto;
    }
}
