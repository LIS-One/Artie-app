package com.arty.roadmapservice.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    

}
