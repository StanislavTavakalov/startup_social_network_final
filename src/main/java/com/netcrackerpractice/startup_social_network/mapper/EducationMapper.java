package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.EducationDTO;
import com.netcrackerpractice.startup_social_network.entity.Education;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {


    EducationDTO toDtoWithoutAccount(Education education);

    @InheritInverseConfiguration
    Education dtoToEntity(EducationDTO educationDTO);
}
