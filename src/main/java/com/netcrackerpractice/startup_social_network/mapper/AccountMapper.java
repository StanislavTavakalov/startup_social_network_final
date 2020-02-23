package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.DetailAccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class, EducationMapper.class, WorkExperienceMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(target = "image", ignore = true)
    DetailAccountDTO entityToDto(Account account);

    @InheritInverseConfiguration
    Account dtoToEntity(DetailAccountDTO accountDTO);
}
