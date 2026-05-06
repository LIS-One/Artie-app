package com.arty.roadmapservice.dto.response.user;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime birthDate;
    private int age;
    private List<Long> roadmapsId;
}
