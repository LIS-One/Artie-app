package com.arty.authservice.service;


import com.arty.authservice.dto.AuthUserDto;
import com.arty.authservice.dto.JwtAuthenticationDto;
import com.arty.authservice.dto.RefreshTokenDto;
import com.arty.authservice.dto.ResponseUserDto;


public interface AuthService {
    JwtAuthenticationDto login( AuthUserDto authUserDto);

    JwtAuthenticationDto refreshToken( RefreshTokenDto refreshTokenDto);

    ResponseUserDto registerUser(AuthUserDto userDto);

    ResponseUserDto getUserByEmail(String email);

    ResponseUserDto getUserById(Long id);
}
