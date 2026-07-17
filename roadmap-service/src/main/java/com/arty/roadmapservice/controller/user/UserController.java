package com.arty.roadmapservice.controller.user;

import com.arty.roadmapservice.dto.constants.apipaths.ApiPaths;
import com.arty.roadmapservice.dto.request.user.UserCreateDto;
import com.arty.roadmapservice.dto.request.user.UserUpdateDto;
import com.arty.roadmapservice.dto.response.activity.ActivityResponseDto;
import com.arty.roadmapservice.dto.response.user.UserResponseDto;
import com.arty.roadmapservice.service.activity.ActivityLogService;
import com.arty.roadmapservice.service.user.UserService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.USERS)
public class UserController {
    private final UserService userService;
    private final ActivityLogService activityLogService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateDto user, Principal principal) throws ServletException, IOException {
        return userService.createUser(user, principal.getName());
    }

    @GetMapping(ApiPaths.BY_ID)
    public UserResponseDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @PostMapping(ApiPaths.BY_ID)
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserUpdateDto user){
        return userService.updateUser(id,user);
    }
    @DeleteMapping(ApiPaths.BY_ID)
    public Boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping(ApiPaths.USER_ACTIVITIES)
    public List<ActivityResponseDto> getUsersActivitiesList(Principal principal){
        return activityLogService.getRecentUserActivities(principal.getName());
    }
}
