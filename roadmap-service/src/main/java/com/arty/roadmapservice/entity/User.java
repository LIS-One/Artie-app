package com.arty.roadmapservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
//    private String password;
    private LocalDateTime birthDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Roadmap> roadmaps;
}
