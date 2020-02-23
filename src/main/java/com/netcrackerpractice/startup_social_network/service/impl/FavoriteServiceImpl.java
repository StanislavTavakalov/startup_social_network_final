package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.FavoriteRepository;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccountToFavorite(Favorite favorite, UUID id) {
        if (!isAdded(favorite, id)) {
            Account account = accountRepository.findById(id).get();
            account.addFavorite(favorite);
            favoriteRepository.save(favorite);
        }
    }

    private boolean isAdded(Favorite favorite, UUID id) {
        Account account = accountRepository.findById(id).get();
        for (Favorite fav : account.getFavorites()) {
            Account acc = fav.getAccount();
            if (acc.getId().equals(favorite.getAccount().getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteFavorite(UUID id, UUID id_account) {
        Account account = accountRepository.findById(id_account).get();
        Favorite favorite = favoriteRepository.findById(id);
        account.removeFavorite(favorite);
        System.out.println(id);
        favoriteRepository.deleteById(id);
    }

    @Override
    public void deleteFavoriteByAccount(UUID id_account, UUID id_deleted_account) {
        Account account = accountRepository.findById(id_account).get();
        Account deltedAccount = accountRepository.findById(id_deleted_account).get();
        UUID favId = null;
        List<Favorite> favoriteList = new ArrayList<>(account.getFavorites());
        for (Favorite favorite : favoriteList) {
            if (favorite.getAccount().getId().equals(id_deleted_account)) {
                favId = favorite.getId();
                account.removeFavorite(favorite);
            }
        }
        if (favId != null) {
            favoriteRepository.deleteById(favId);
        }
    }
}
