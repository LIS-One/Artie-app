package com.arty.roadmapservice.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserUpdateDto {
    String firstName;
    String lastName;
    String email;
    LocalDateTime birthDate;

}
