package com.arty.roadmapservice.dto.request.activity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityUpdateDto {
    private String name;
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private LocalDateTime ended;

}
