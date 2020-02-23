package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private UUID id;
    private RoleEnum roleName;

}
