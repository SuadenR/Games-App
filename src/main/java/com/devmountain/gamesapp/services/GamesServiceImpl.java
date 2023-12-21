package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.repositories.GamesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamesServiceImpl implements GamesService{

    @Autowired
    private GamesRepository gamesRepository;

    @Override
    @Transactional
    public void addGames(GamesDto gamesDto){
        Games games = new Games(gamesDto);
        gamesRepository.saveAndFlush(games);
    }
    @Override
    @Transactional
    public void deleteGamesById(Long gamesId){
        Optional<Games> gamesOptional = gamesRepository.findGamesById(gamesId);
        gamesOptional.ifPresent(games -> gamesRepository.delete(games));
    }

    @Override
    @Transactional
    public Optional<Games> updateGames(GamesDto gamesDto) {
        Optional<Games> gamesOptional = gamesRepository.findGamesById(gamesDto.getId());
        gamesOptional.ifPresent(games -> {
            games.setGameTitle(gamesDto.getGameTitle());
            games.setGenre(gamesDto.getGenre());
            games.setPublisher(gamesDto.getPublisher());
            games.setPlatform(gamesDto.getPlatform());
            games.setRating(gamesDto.getRating());
            gamesRepository.saveAndFlush(games);
        });
        return gamesOptional;
    }

    @Override
    public Optional<GamesDto> findGamesById(Long gamesId) {
        Optional<Games> gamesOptional = gamesRepository.findGamesById(gamesId);
        return gamesOptional.map(GamesDto::new);
    }


}
