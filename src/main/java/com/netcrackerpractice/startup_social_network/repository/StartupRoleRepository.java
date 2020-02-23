package com.netcrackerpractice.startup_social_network.repository;


import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StartupRoleRepository extends JpaRepository<StartupRole, UUID> {
    @Query("select sr from StartupRole sr where sr.account.id = ?1 and sr.startup.id = ?2 and sr.roleName = 'MODERATOR'")
    Optional<StartupRole> findModeratorInStartup (UUID accountId, UUID startupId);
}
