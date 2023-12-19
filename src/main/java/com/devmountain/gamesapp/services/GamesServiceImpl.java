package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.repositories.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamesServiceImpl implements GamesService{

    @Autowired
    private GamesRepository gamesRepository;
    @Override
    public List<String> addGames(GamesDto gamesDto){
        List<String> response = new ArrayList<>();
        Games games = new Games(gamesDto);
        gamesRepository.saveAndFlush(games);
        response.add("http://localhost:8081/new-games.html");
        return response;
    }
}
