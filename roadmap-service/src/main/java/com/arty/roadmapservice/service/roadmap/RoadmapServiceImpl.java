package com.arty.roadmapservice.service.roadmap;

import com.arty.roadmapservice.dto.request.roadmap.RoadmapCreateDto;
import com.arty.roadmapservice.dto.request.roadmap.RoadmapUpdateDto;
import com.arty.roadmapservice.dto.response.roadmap.RoadmapResponseDto;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.repository.RoadmapRepository;
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

    @Transactional
    @Override
    public RoadmapResponseDto createRoadmap(RoadmapCreateDto roadmapCreateDto) {
        Roadmap roadmap = modelMapper.map(roadmapCreateDto, Roadmap.class);
        roadmapRepository.save(roadmap);
        return modelMapper.map(roadmap, RoadmapResponseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public RoadmapResponseDto getRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        return roadmap.map(value->modelMapper.map(value,RoadmapResponseDto.class)).orElse(null);
    }

    @Transactional
    @Override
    public RoadmapResponseDto updateRoadmap(Long id, RoadmapUpdateDto roadmapUpdateDto) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);

       return roadmap.map(value->{
            modelMapper.map(roadmapUpdateDto,value);
            roadmapRepository.save(value);
            return modelMapper.map(value,RoadmapResponseDto.class);
        }).orElse(null);
    }

    @Transactional
    @Override
    public Boolean deleteRoadmap(Long id) {
        Optional<Roadmap> roadmap = roadmapRepository.findById(id);
        roadmap.ifPresent(roadmapRepository::delete);
        return !roadmapRepository.existsById(id);
    }
}
