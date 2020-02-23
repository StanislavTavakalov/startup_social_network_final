package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.StartupRoleDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import com.netcrackerpractice.startup_social_network.entity.enums.StartupRoleEnum;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

//@Mapper(componentModel = "spring",
//        unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface StartupRoleMapper {
//
//
//    @Mapping( target = "account.birthday", ignore=true)
//    @Mapping( target = "account.user", ignore=true)
//    @Mapping( target = "account.aboutMe", ignore=true)
//    @Mapping( target = "account.resumes", ignore=true)
//    @Mapping( target = "account.startups", ignore=true)
//    @Mapping( target = "account.startupRoles", ignore=true)
//    @Mapping( target = "account.favorites", ignore=true)
//    @Mapping( target = "account.educations", ignore=true)
//    @Mapping( target = "account.workExperiences", ignore=true)
//    @Mapping( target = "account.imageId", ignore=true)
//    @Mapping( target = "account.compressedImageId", ignore=true)
//    @Mapping( target = "startup.startupName", ignore=true)
//    @Mapping( target = "startup.idea", ignore=true)
//    @Mapping( target = "startup.aboutProject", ignore=true)
//    @Mapping( target = "startup.businessPlan", ignore=true)
//    @Mapping( target = "startup.sumOfInvestment", ignore=true)
//    @Mapping( target = "startup.dateOfCreation", ignore=true)
//    @Mapping( target = "startup.imageId", ignore=true)
//    @Mapping( target = "startup.compressedImageId", ignore=true)
//    @Mapping( target = "startup.nonBlock", ignore=true)
//    @Mapping( target = "startup.account", ignore=true)
//    @Mapping( target = "startup.startupResumes", ignore=true)
//    @Mapping( target = "startup.startupRoles", ignore=true)
//    @Mapping( target = "startup.startupInvestments", ignore=true)
//    StartupRoleDTO entityToDto(StartupRole startupRole);
//
//    @InheritInverseConfiguration
//    StartupRole dtoToEntity(StartupRoleDTO startupRoleDTO);
//}


@Mapper(componentModel = "spring")
public abstract class StartupRoleMapper {
    @Autowired
    private AccountService accountService;
    @Autowired
    private StartupService startupService;

    public StartupRole dtoToEntity(StartupRoleDTO startupRoleDTO) {
        StartupRole startupRole = new StartupRole();
        startupRole.setId(startupRoleDTO.getId());
        startupRole.setAccount(accountService.findAccountById(startupRoleDTO.getAccountId()).get());
        startupRole.setStartup(startupService.findStartupById(startupRoleDTO.getStartupId()).get());
        startupRole.setRoleName(StartupRoleEnum.valueOf(startupRoleDTO.getRoleName().toUpperCase()));
        return startupRole;
//        return StartupRole.builder()
//                .id(startupRoleDTO.getId())
//                .account(accountService.findAccountById(startupRoleDTO.getAccountId()).get())
//                .startup(startupService.findStartupById(startupRoleDTO.getStartupId()).get())
//                .roleName(StartupRoleEnum.valueOf(startupRoleDTO.getRoleName().toUpperCase()))
//                .build();
    }

    public StartupRoleDTO entityToDto(StartupRole startupRole) {
        StartupRoleDTO startupDTO = new StartupRoleDTO();
        startupDTO.setId(startupRole.getId());
        startupDTO.setAccountId(startupRole.getAccount().getId());
        startupDTO.setStartupId(startupRole.getStartup().getId());
        startupDTO.setRoleName(startupRole.getRoleName().toString());
        return startupDTO;
//        return StartupRoleDTO.builder()
//                .id(startupRole.getId())
//                .accountId(startupRole.getAccount().getId())
//                .startupId(startupRole.getStartup().getId())
//                .roleName(startupRole.getRoleName().toString())
//                .build();
    }

    public abstract Set<StartupRoleDTO> entityToDto(Collection<StartupRoleDTO> startupRoleDTOS);
    // public abstract List<MessageDTO> messageToMessageDTO(Collection<Message> messages);
}