package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.ResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring",uses = {StartupResumeMapper.class, AccountMapper.class})
public interface ResumeMapper {

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
//    @Mapping( target = "account.compressedImageId", ignore = true)
    @Mapping(target = "account.yourContact", ignore = true)
    @Mapping(target = "account.otherContact", ignore = true)
    @Mapping(target = "account.yourConversations", ignore = true)
    @Mapping(target = "account.otherConversations", ignore = true)
    @Mapping(target = "account.sendMessages", ignore = true)
    @Mapping(target = "account.receivedMessages", ignore = true)
        // @Mapping( target = "account.imageId", ignore=true)
    //@Mapping(target = "businessRole.resumes", ignore = true)
    ResumeDTO entityToDto(Resume resume);

    @InheritInverseConfiguration
    Resume dtoToEntity(ResumeDTO resumeDTO);

}
