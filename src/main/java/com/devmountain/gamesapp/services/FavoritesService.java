package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.FavoritesDto;

import com.devmountain.gamesapp.dtos.GamesDto;
import jakarta.transaction.Transactional;

import java.util.Optional;


public interface FavoritesService {

    @Transactional
    void addFavorites(GamesDto gamesDto, FavoritesDto favoritesDto, Long userId);

    @Transactional
    void deleteFavoritesById(Long favoritesId);
}
