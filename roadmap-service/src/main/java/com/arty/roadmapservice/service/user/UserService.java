package com.arty.roadmapservice.service.user;

import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);

    UserResponseDto getUser(Long userId);

    UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto);

    Boolean deleteUser(Long userId);
}
