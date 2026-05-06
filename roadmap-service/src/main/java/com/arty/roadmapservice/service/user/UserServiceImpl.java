package com.arty.roadmapservice.service.user;

import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import com.arty.roadmapservice.entity.User;
import com.arty.roadmapservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto,User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(value -> modelMapper.map(value, UserResponseDto.class)).orElse(null);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            User mappedUser = user.get();
           modelMapper.map(userUpdateDto,mappedUser);
            userRepository.save(user.get());
            return modelMapper.map(user.get(), UserResponseDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.deleteById(user.get().getId());
            return true;
        }
        return false;
    }
}
