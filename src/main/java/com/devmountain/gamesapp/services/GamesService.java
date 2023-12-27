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
    @Transactional
    Optional<Games> updateGames(GamesDto gamesDto);

    Optional<GamesDto> findGamesById(Long gamesId);

    @Transactional
    String addGamesToFavorites(Long gamesId, String favoritesName, Long userId);

    List<GamesDto> getAllGames();
}
