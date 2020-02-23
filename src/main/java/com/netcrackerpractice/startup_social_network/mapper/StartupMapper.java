package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class, StartupResumeMapper.class, StartupRoleMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StartupMapper {
    @Mapping(target = "account.startups", ignore = true)
    @Mapping(target = "account.aboutMe", ignore = true)
    @Mapping(target = "account.resumes", ignore = true)
    @Mapping(target = "account.yourContact", ignore = true)
    @Mapping(target = "account.otherContact", ignore = true)
    @Mapping(target = "account.otherConversations", ignore = true)
    @Mapping(target = "account.startupRoles", ignore = true)
    @Mapping(target = "account.favorites", ignore = true)
    @Mapping(target = "account.educations", ignore = true)
    @Mapping(target = "account.workExperiences", ignore = true)
    @Mapping(target = "account.yourConversations", ignore = true)
    @Mapping(target = "account.sendMessages", ignore = true)
    @Mapping(target = "account.receivedMessages", ignore = true)
    // @Mapping(target = "startupRoles", ignore = true)
    @Mapping(target = "image", ignore = true)
    StartupDTO entityToDto(Startup startup);

    @InheritInverseConfiguration
    Startup dtoToEntity(StartupDTO startupDTO);
}
