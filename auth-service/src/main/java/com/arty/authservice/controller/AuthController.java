package com.arty.authservice.controller;

import com.arty.authservice.dto.AuthUserDto;
import com.arty.authservice.dto.ResponseUserDto;

import com.arty.authservice.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final AuthServiceImpl authService;


    @PostMapping
    public ResponseUserDto RegisterUser(@RequestBody AuthUserDto authUserDto){
        return authService.registerUser(authUserDto);
    }



}
