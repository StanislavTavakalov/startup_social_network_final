package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM accounts a WHERE a.id IN" +
            "(SELECT c.id_contact_account FROM contacts c WHERE c.id_your_account = :userId)",
            nativeQuery = true)
    List<Account> getUserContacts(@Param("userId") UUID userId);

    @Query(value = "INSERT INTO contacts(id, id_your_account, id_contact_account) " +
            "VALUES (uuid_generate_v4(), :whoAddId, :whomAddId)",
            nativeQuery = true)
    @Modifying
    @Transactional
    void addUserInContacts(@Param("whoAddId") UUID whoAddId, @Param("whomAddId") UUID whomAddId);

    @Query(value = "DELETE FROM contacts c WHERE c.id_your_account = :whoDeleteId AND c.id_contact_account = :whomDeleteId",
            nativeQuery = true)
    @Modifying
    @Transactional
    void deleteUserFromContacts(@Param("whoDeleteId") UUID whoAddId, @Param("whomDeleteId") UUID whomAddId);
}
