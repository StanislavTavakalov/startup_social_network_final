package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.BusinessRoleDTO;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class BusinessRoleMapper {

    public BusinessRoleDTO entityToDto(BusinessRole skill) {
        return BusinessRoleDTO.builder()
                .id(skill.getId())
                .businessRoleName(skill.getBusinessRoleName())
                .build();

    }

    public BusinessRole dtoToEntity(BusinessRoleDTO businessRoleDTO) {
        return BusinessRole.builder()
                .id(businessRoleDTO.getId())
                .businessRoleName(businessRoleDTO.getBusinessRoleName())
                .build();

    }
}
