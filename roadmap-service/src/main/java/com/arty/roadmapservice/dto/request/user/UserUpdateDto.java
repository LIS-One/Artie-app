package com.arty.roadmapservice.dto.request.user;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String email;

}
