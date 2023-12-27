package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.FavoritesDto;

import jakarta.transaction.Transactional;

import java.util.List;


public interface FavoritesService {

    @Transactional
    void addFavoritesToUser(FavoritesDto favoritesDto, Long userId);

    @Transactional
    void deleteFavorites(Long favoritesId);

    List<FavoritesDto> getFavoritesByUserId(Long userId);

    List<FavoritesDto> getAllFavorites();
}
