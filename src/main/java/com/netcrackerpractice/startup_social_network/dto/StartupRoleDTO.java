package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupRoleDTO {
    private UUID id;
    @NotNull
    private UUID startupId;
    @NotNull
    private UUID accountId;
    @NotNull
    private String roleName;

}
