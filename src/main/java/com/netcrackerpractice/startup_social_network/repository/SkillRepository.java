package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findSkillById(UUID id);

    Skill findBySkillName(String string);
}
