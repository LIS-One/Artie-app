package com.arty.roadmapservice.service.user;

import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import com.arty.roadmapservice.entity.UserProfile;

public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto, String name);

    UserResponseDto getUser(Long userId);

    UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto);

    UserProfile getOrCreateByEmail(String email);

    Boolean deleteUser(Long userId);
}
