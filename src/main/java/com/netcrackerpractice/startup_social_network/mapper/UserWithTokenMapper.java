package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.UserDTOwithToken;
import com.netcrackerpractice.startup_social_network.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserWithTokenMapper {

    @Mapping(target = "account.user", ignore = true)
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
    @Mapping(target = "account.receivedMessages", ignore = true)
    @Mapping(target = "account.sendMessages", ignore = true)
    @Mapping(target = "account.yourConversations", ignore = true)
    UserDTOwithToken entityToDto(User user);

    @InheritInverseConfiguration
    User dtoToEntity(UserDTOwithToken user);

}