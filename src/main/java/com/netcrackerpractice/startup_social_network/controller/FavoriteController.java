package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.FavoriteDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.mapper.FavoriteMapper;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.FavoriteRepository;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @GetMapping("/favorites/{id}")
    public List<FavoriteDTO> getFavorites(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).get();
        List<FavoriteDTO> favoriteDTOS = new ArrayList<>();
        account.getFavorites().forEach(favorite -> favoriteDTOS.add(favoriteMapper.entityToDto(favorite)));
        return favoriteDTOS;
    }

    @DeleteMapping("/favorites/{id}")
    public void deleteFavorite(@PathVariable UUID id, @RequestParam(name = "id_account") UUID id_account) {
        favoriteService.deleteFavorite(id, id_account);
    }


}
