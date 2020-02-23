package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Role;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleName(RoleEnum roleName);
}
