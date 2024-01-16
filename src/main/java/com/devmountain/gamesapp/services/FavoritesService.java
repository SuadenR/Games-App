package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.FavoritesDto;

import com.devmountain.gamesapp.dtos.GamesDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


public interface FavoritesService {

    @Transactional
    void addFavoritesToUser(FavoritesDto favoritesDto, Long userId);

    @Transactional
    void deleteFavorites(Long favoritesId);

    List<FavoritesDto> getFavoritesByUserId(Long userId);

    List<FavoritesDto> getAllFavorites();

    List<GamesDto> getAllGamesFromFavoriteByUserId(Long userId);

    Optional<FavoritesDto> getFavoritesById(Long favoritesId);
}
