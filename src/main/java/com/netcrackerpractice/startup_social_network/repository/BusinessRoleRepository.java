package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessRoleRepository extends JpaRepository<BusinessRole, UUID> {
    BusinessRole findBusinessRoleByBusinessRoleName(BusinessRoleEnum businessRoleEnum);
}
