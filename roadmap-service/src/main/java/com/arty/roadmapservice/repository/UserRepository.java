package com.arty.roadmapservice.repository;

import com.arty.roadmapservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
