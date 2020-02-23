package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.SkillDTO;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public class SkillMapper {

    public SkillDTO entityToDto(Skill skill) {
        SkillDTO skillDTO = SkillDTO.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .build();
        return skillDTO;
    }

    public Skill dtoToEntity(SkillDTO skillDTO) {
        Skill skill = Skill.builder()
                .id(skillDTO.getId())
                .skillName(skillDTO.getSkillName())
                .build();
        return skill;
    }
}
