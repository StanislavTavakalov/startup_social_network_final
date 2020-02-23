package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StartupResumeMapper {

    @Mapping(target = "startup.startupName", ignore = true)
    @Mapping(target = "startup.idea", ignore = true)
    @Mapping(target = "startup.aboutProject", ignore = true)
    @Mapping(target = "startup.businessPlan", ignore = true)
    @Mapping(target = "startup.sumOfInvestment", ignore = true)
    @Mapping(target = "startup.dateOfCreation", ignore = true)
    @Mapping(target = "startup.imageId", ignore = true)
    @Mapping(target = "startup.compressedImageId", ignore = true)
    @Mapping(target = "startup.nonBlock", ignore = true)
    @Mapping(target = "startup.account", ignore = true)
    @Mapping(target = "startup.startupResumes", ignore = true)
    @Mapping(target = "startup.startupRoles", ignore = true)
    @Mapping(target = "startup.startupInvestments", ignore = true)
    StartupResumeDTO entityToDto(StartupResume startupResume);

    @InheritInverseConfiguration
    StartupResume dtoToEntity(StartupResumeDTO startupResumeDTO);
//       StartupResumeDTO startupResumeToDto(StartupResume startupResume) {
//              StartupResumeDTO startupResumeDTO = StartupResumeDTO.builder()
//                      .id(startupResume.getId())
//                      .resumeId(startupResume.getResume().getId())
//                      .build();
//              return startupResumeDTO;
//       }
//
//       ResumeStartupDTO resumeStartupToDto(StartupResume startupResume) {
//              ResumeStartupDTO resumeStartupDTO = ResumeStartupDTO.builder()
//                      .id(startupResume.getId())
//                      .status(startupResume.getStatus())
//                      .startupId(startupResume.getStartup().getId())
//                      .startupName(startupResume.getStartup().getStartupName())
//                      .idea(startupResume.getStartup().getIdea())
//                      .aboutProject(startupResume.getStartup().getAboutProject())
//                      .businessPlan(startupResume.getStartup().getBusinessPlan())
//                      .sumOfInvestment(startupResume.getStartup().getSumOfInvestment())
//                      .build();
//              return resumeStartupDTO;
//       }
}


//@Mapper(componentModel = "spring")
//public abstract class StartupResumeMapper {
//
//       @Autowired
//       ResumeMapper resumeMapper;
//
//       @Autowired
//       StartupRepository startupRepository;
//
//       public StartupResumeDTO entityToDto(StartupResume startupResume) {
//              if (startupResume == null) {
//                     return null;
//              } else {
//                     StartupResumeDTO startupResumeDTO = new StartupResumeDTO();
//                     startupResumeDTO.setId(startupResume.getId());
//                     startupResumeDTO.setAccepted(startupResume.isAccepted());
//                     startupResumeDTO.setResume(this.resumeMapper.entityToDto(startupResume.getResume()));
//                     startupResumeDTO.setId(startupResume.getStartup().getId());
//                     return startupResumeDTO;
//              }
//       }
//
//       public StartupResume dtoToEntity(StartupResumeDTO startupResumeDTO) {
//              if (startupResumeDTO == null) {
//                     return null;
//              } else {
//                     StartupResume startupResume = new StartupResume();
//                     startupResume.setId(startupResumeDTO.getId());
//                     startupResume.setAccepted(startupResumeDTO.isAccepted());
//                     startupResume.setResume(this.resumeMapper.dtoToEntity(startupResumeDTO.getResume()));
//                     startupResume.setStartup(this.startupRepository.findById(startupResumeDTO.getStartupId()).get());
//                     return startupResume;
//              }
//       }
//
//}

