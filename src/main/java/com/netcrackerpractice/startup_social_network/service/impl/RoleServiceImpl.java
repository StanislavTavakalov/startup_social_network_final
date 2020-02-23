package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Role;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import com.netcrackerpractice.startup_social_network.repository.RoleRepository;
import com.netcrackerpractice.startup_social_network.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRoleName(RoleEnum roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
