package com.arty.authservice.service;

import com.arty.authservice.dto.AuthUserDto;
import com.arty.authservice.dto.JwtAuthenticationDto;
import com.arty.authservice.dto.RefreshTokenDto;
import com.arty.authservice.dto.ResponseUserDto;
import com.arty.authservice.entity.User;
import com.arty.authservice.repository.UserRepository;
import com.arty.authservice.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public JwtAuthenticationDto login(AuthUserDto authUserDto) {
        return null;
    }

    @Override
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {
        return null;
    }

    @Override
    public ResponseUserDto registerUser(AuthUserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user,ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto getUserByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email).orElseThrow(), ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto getUserById(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(), ResponseUserDto.class);
    }

    private User findByCredentials(AuthUserDto authUserDto) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(authUserDto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(passwordEncoder.matches(authUserDto.getPassword(),user.getPassword())){
                return user;
            }
        }
        throw new AuthenticationException("Invalid username or password");
    }

    private User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(() -> new Exception("Email not found"));
    }
}
