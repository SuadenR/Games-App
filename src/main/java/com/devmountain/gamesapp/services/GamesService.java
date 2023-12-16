package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.GamesDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GamesService{
    @Transactional
    List<String> addGames(GamesDto gamesDto);

}
