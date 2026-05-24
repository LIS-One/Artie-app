package com.arty.roadmapservice.entity;

import com.arty.roadmapservice.dto.constants.enums.LogMode;
import com.arty.roadmapservice.dto.constants.enums.LogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime ended;
    @ManyToOne
    @JoinColumn(name = "roadmap")
    private Roadmap roadmap;
    @ManyToOne
    private Milestone milestone;
    private LogStatus logStatus;
    private LogMode logMode;
}
