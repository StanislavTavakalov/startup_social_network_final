package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Favorite;

import java.util.UUID;

public interface FavoriteService {
    void addAccountToFavorite(Favorite favorite, UUID id);

    void deleteFavorite(UUID id, UUID id_account);

    void deleteFavoriteByAccount(UUID id_account, UUID id_deleted_account);
}
