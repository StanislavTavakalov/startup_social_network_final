package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findById(UUID id);

    List<Favorite> findByAccount(Account account);

    @Query(value = "DELETE FROM favorites WHERE favorites.id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteById(UUID id);
}
