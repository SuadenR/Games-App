package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Favorites;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.repositories.FavoritesRepository;
import com.devmountain.gamesapp.repositories.GamesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamesServiceImpl implements GamesService{

    @Autowired
    private GamesRepository gamesRepository;
    @Autowired
    private FavoritesRepository favoritesRepository;

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

    @Override
    @Transactional
    public String addGamesToFavorites(Long gamesId, String favorites, Long userId){
        Optional<Games> gamesOptional = gamesRepository.findById(gamesId);
        Optional<Favorites> favoritesOptional = favoritesRepository.findFavoritesByNameAndUserId(gamesId, favorites, userId);

        if (gamesOptional.isPresent() && favoritesOptional.isPresent()){
            favoritesOptional.get().getGamesSet().add(gamesOptional.get());
            gamesOptional.get().getFavoritesSet().add(favoritesOptional.get());
            gamesRepository.saveAndFlush(gamesOptional.get());
            favoritesRepository.saveAndFlush(favoritesOptional.get());
            return "Added to your favorites!";
        }
        return "Unable to add to your favorites.";
    }


}
