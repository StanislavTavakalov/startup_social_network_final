package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {
    Optional<Startup> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Startup> findStartupsByAccount_Id(UUID account);

    @Query(value = "SELECT s FROM Startup s JOIN s.startupResumes sr" +
            " JOIN sr.resume r " +
            " JOIN r.account " +
            "a WHERE a.id = ?1 and sr.accepted = true and LOWER(s.startupName) LIKE %?2% ")
    List<Startup> searchStartupsAsMember(UUID accountId, String nameContains, Sort sort);




    @Query(value = "SELECT s FROM Startup s " +
            "JOIN s.account a " +
            "WHERE LOWER(s.startupName) LIKE %?1% and a.id = ?2")
    List<Startup> searchStartupAsLeader(String startupNameContains, UUID accountId, Sort sort);


    @Query(value = "SELECT s FROM Startup s " +
            "JOIN s.account a " +
            "JOIN a.user u " +
            "WHERE LOWER(s.startupName) LIKE %?1% and  LOWER(u.login) LIKE %?2%")
    List<Startup> searchAllStartups(String startupNameContains, String loginContains, Sort sort);

    Optional<Startup> findByStartupName (String startupName);

    @Query("select s from Startup s where s.id = ?1 and s.account.id = ?2")
    Optional<Startup> findStartupByIdAndAccountId(UUID startupId, UUID accountId);


    @Modifying()
    @Query( value = "UPDATE startups SET non_block=false  WHERE id = ?1", nativeQuery = true)
    void blockStartup(UUID id);

    @Modifying
    @Query( value = "UPDATE startups SET non_block=true  WHERE id = ?1", nativeQuery = true)
    void unBlockStartup(UUID id);

}
