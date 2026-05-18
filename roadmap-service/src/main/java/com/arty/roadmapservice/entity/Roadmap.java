package com.arty.roadmapservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Roadmap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roadmapName;
    private String roadmapDescription;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(nullable = true)
    private LocalDateTime expirationDate;
    @OneToMany(mappedBy = "attachedToRoadmap", cascade = CascadeType.ALL)
    private List<Milestone> milestoneList;
    @OneToMany(mappedBy = "attachedToRoadmap", cascade = CascadeType.ALL)
    private List<ActivityLog> logsList;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private boolean finishedStatus;

}
