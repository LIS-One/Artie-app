package com.arty.roadmapservice.service.roadmap;

import com.arty.roadmapservice.dto.constants.enums.RoadMilestoneStatus;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.entity.UserProfile;
import com.arty.roadmapservice.repository.RoadmapRepository;
import com.arty.roadmapservice.repository.UserRepository;
import com.arty.roadmapservice.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoadmapServiceImpl implements RoadmapService{
    private final RoadmapRepository roadmapRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Transactional
    @Override
    public RoadmapResponseDto createRoadmap(RoadmapCreateDto roadmapCreateDto, String email) {
        UserProfile user = userService.getOrCreateByEmail(email);
        Roadmap roadmap = modelMapper.map(roadmapCreateDto, Roadmap.class);
        roadmap.setUserProfile(user);
        roadmapRepository.save(roadmap);
        return toDto(roadmap);
    }

    @Transactional(readOnly = true)
    @Override
    public RoadmapResponseDto getRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        return roadmap.map(this::toDto).orElse(null);
    }

    @Transactional
    @Override
    public RoadmapResponseDto updateRoadmap(Long id, RoadmapUpdateDto roadmapUpdateDto) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        if(roadmap.isPresent()) {
            roadmap.get().setName(roadmapUpdateDto.getName());
            roadmap.get().setDescription(roadmapUpdateDto.getDescription());
            roadmap.get().setExpirationDate(roadmapUpdateDto.getExpirationDate());
            roadmapRepository.save(roadmap.get());
            return toDto(roadmap.get());
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean deleteRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        roadmap.ifPresent(roadmapRepository::delete);
        return !roadmapRepository.existsById(id);
    }

    @Transactional
    @Override
    public RoadmapResponseDto completeRoadmap(Long id) {
        Roadmap roadmap = roadmapRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        roadmap.setStatus(RoadMilestoneStatus.COMPLETED);
        roadmap.markCompleted();
        roadmapRepository.save(roadmap);
        return toDto(roadmap);
    }

    private RoadmapResponseDto toDto(Roadmap r) {
        if (r == null) return null;
        RoadmapResponseDto dto = new RoadmapResponseDto();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setCreationDate(r.getCreationDate());
        dto.setUserProfileId(r.getUserProfile().getId());
        dto.setExpirationDate(r.getExpirationDate());
        dto.setStatus(r.getStatus());
        dto.setMilestoneListId(r.getMilestones().stream().map(Milestone::getId).toList());
        return dto;
    }
}
