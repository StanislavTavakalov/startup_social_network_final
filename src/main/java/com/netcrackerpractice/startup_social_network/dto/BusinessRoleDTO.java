package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessRoleDTO {

    private UUID id;

    private BusinessRoleEnum businessRoleName;
}
