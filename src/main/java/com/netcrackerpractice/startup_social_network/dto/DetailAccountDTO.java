package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailAccountDTO {
    private UUID id;

    @Size(max=255, message="First name should not be greater than 255 characters")
    private String firstName;

    @Size(max=255, message="Last name should not be greater than 255 characters")
    private String lastName;

    @Past
    private Date birthday;

    private UserDTO user;

    @Size(max=255, message="About me should not be greater than 255 characters")
    private String aboutMe;

    private List<ResumeDTO> resumes;

    private List<StartupDTO> startups;

    private List<StartupRoleDTO> startupRoles;

    private List<FavoriteDTO> favorites;

    @Size(max=10, message="Education should have less than 10 items")
    private Set<EducationDTO> educations;

    @Size(max=10, message="Work experience should have less than 10 items")
    private List<WorkExperienceDTO> workExperiences;

    private String imageId;

    private String compressedImageId;

    private String image;
    private int balance;

}