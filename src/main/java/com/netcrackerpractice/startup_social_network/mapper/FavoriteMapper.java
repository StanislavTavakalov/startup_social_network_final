package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.FavoriteDTO;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "account.birthday", ignore = true)
    @Mapping(target = "account.user", ignore = true)
    @Mapping(target = "account.aboutMe", ignore = true)
    @Mapping(target = "account.resumes", ignore = true)
    @Mapping(target = "account.startups", ignore = true)
    @Mapping(target = "account.startupRoles", ignore = true)
    @Mapping(target = "account.favorites", ignore = true)
    @Mapping(target = "account.educations", ignore = true)
    @Mapping(target = "account.workExperiences", ignore = true)
    @Mapping(target = "account.imageId", ignore = true)
    @Mapping(target = "account.yourContact", ignore = true)
    @Mapping(target = "account.otherContact", ignore = true)
    @Mapping(target = "account.yourConversations", ignore = true)
    @Mapping(target = "account.otherConversations", ignore = true)
    @Mapping(target = "account.sendMessages", ignore = true)
    @Mapping(target = "account.receivedMessages", ignore = true)
    FavoriteDTO entityToDto(Favorite favorite);

    @InheritInverseConfiguration
    Favorite dtoToEntity(FavoriteDTO accountDTO);
}
