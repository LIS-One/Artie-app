package com.arty.roadmapservice.service.roadmap;

import com.arty.roadmapservice.dto.constants.enums.Status;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.repository.RoadmapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoadmapServiceImpl implements RoadmapService{
    private final RoadmapRepository roadmapRepository;
    private final ModelMapper modelMapper;
    @Override
    public RoadmapResponseDto createRoadmap(RoadmapCreateDto roadmapCreateDto) {
        Roadmap roadmap = modelMapper.map(roadmapCreateDto, Roadmap.class);
        roadmap.setStatus(Status.WAITING);
        roadmapRepository.save(roadmap);
        return modelMapper.map(roadmap, RoadmapResponseDto.class);
    }

    @Override
    public RoadmapResponseDto getRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        return roadmap.map(value->modelMapper.map(value,RoadmapResponseDto.class)).orElse(null);
    }

    @Override
    public RoadmapResponseDto updateRoadmap(Long id, RoadmapUpdateDto roadmapUpdateDto) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);

       return roadmap.map(value->{
            modelMapper.map(roadmapUpdateDto,value);
            roadmapRepository.save(value);
            return modelMapper.map(value,RoadmapResponseDto.class);
        }).orElse(null);
    }

    @Override
    public Boolean deleteRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        roadmap.ifPresent(roadmapRepository::delete);
        return !roadmapRepository.existsById(id);
    }
}
