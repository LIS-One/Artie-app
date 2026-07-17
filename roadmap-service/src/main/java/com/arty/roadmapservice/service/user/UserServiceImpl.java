package com.arty.roadmapservice.service.user;

import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.milestone.MilestoneResponseDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import com.arty.roadmapservice.entity.Milestone;
import com.arty.roadmapservice.entity.Roadmap;
import com.arty.roadmapservice.entity.UserProfile;
import com.arty.roadmapservice.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto,String email) {
        UserProfile user = new UserProfile();
        modelMapper.map(userCreateDto,user);
        user.setEmail(email);

        userRepository.save(user);
        return toDto(user);
    }



    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getUser(Long userId) {
        Optional<UserProfile> user = userRepository.findById(userId);

       return user.map(this::toDto).orElse(null);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<UserProfile> user = userRepository.findById(id);

        if(user.isPresent()){
            UserProfile mappedUser = user.get();
           modelMapper.map(userUpdateDto,mappedUser);
            userRepository.save(user.get());
            return toDto(mappedUser);
        }
        return null;
    }

    @Override
    @Transactional
    public UserProfile getOrCreateByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    UserProfile u = new UserProfile();
                    u.setEmail(email);
                    return userRepository.save(u);
                });
    }

    @Transactional
    @Override
    public Boolean deleteUser(Long userId) {
        Optional<UserProfile> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.deleteById(user.get().getId());
            return true;
        }
        return false;
    }

    private UserResponseDto toDto(UserProfile u) {
        if (u == null) return null;
        UserResponseDto dto = new UserResponseDto();
        dto.setId(u.getId());
        dto.setFirstName(u.getFirstName());
        dto.setLastName(u.getLastName());
        dto.setEmail(u.getEmail());
        dto.setBirthDate(u.getBirthDate());
        dto.setRoadmapsId(u.getRoadmaps().stream().map(Roadmap::getId).toList());
        return dto;
    }
}
