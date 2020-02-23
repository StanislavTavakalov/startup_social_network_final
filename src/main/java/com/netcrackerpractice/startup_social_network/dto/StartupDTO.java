package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupDTO {
    private UUID id;
    @NotNull
    @Size(max = 20, message = "Max length of startup name is 20 symbols")
    private String startupName;
    private Account account;
    @Size(max=150, message = "Idea size is too big. It should be less then 150 symbols")
    private String idea;
    @Size(max=255, message = "About project is too big. It should be less then 255 symbols")
    private String aboutProject;
    @Size(max=255, message = "Idea size is too big. It should be less then 255 symbols")
    private String businessPlan;
    @NotNull
    @Min(value = 0)
    @Max(value=Integer.MAX_VALUE, message = "Sum of investment is invalid.")
    private int sumOfInvestment;
    private Set<StartupResumeDTO> startupResumes;
    private String imageId;
    private String compressedImageId;
    private String image;
    @Valid
    private Set<Investment> startupInvestments;
    @Valid
    private Set<StartupRoleDTO> startupRoles = new HashSet<>();
    private Timestamp dateOfCreation;
    private boolean nonBlock;

    public Set<StartupRoleDTO> getStartupRoles() {
        return startupRoles;
    }

    public void setStartupRoles(Set<StartupRoleDTO> startupRoles) {
        if (startupRoles != null && startupRoles.size() != 0) {
            this.startupRoles.clear();
            this.startupRoles.addAll(startupRoles);
        }
    }
}
