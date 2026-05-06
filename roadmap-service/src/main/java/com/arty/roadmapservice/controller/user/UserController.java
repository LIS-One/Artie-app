package com.arty.roadmapservice.controller.user;

import com.arty.roadmapservice.dto.constants.ApiPaths;
import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import com.arty.roadmapservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.PATH_USER_BASE)
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateDto user){
        return userService.createUser(user);
    }

    @GetMapping
    public UserResponseDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @PostMapping
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserUpdateDto user){
        return userService.updateUser(id,user);
    }
    @DeleteMapping
    public Boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
