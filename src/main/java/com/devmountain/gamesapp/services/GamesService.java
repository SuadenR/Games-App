package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Games;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface GamesService{

    @Transactional
    void addGames(GamesDto gamesDto);
    @Transactional
    void  deleteGamesById(Long gamesId);

    Optional<GamesDto> findGamesById(Long gamesId);

    @Transactional
    String addGamesToFavorites(Long gamesId, Long favoritesId, Long userId);

    @Transactional
    String addGamesToFavorites(Long gamesId, Long userId);

    List<GamesDto> getAllGames();

    @Transactional
    void updateGames(GamesDto gamesDto);
}
