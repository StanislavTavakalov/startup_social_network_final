package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private UserDTO user;
    private String aboutMe;
    private String imageId;
    private String compressedImageId;
    private boolean nonBlock;
    private List<ResumeDTO> resumes;
    private List<ContactDTO> yourContact;
    private List<ConversationDTO> yourConversations;
    private List<StartupDTO> startups;
    private List<StartupRoleDTO> startupRoles;
    private List<FavoriteDTO> favorites;
    private Set<Education> educations;
    private List<WorkExperienceDTO> workExperiences;
}
