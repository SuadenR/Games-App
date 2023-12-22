package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.FavoritesDto;
import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Favorites;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.entities.User;
import com.devmountain.gamesapp.repositories.FavoritesRepository;
import com.devmountain.gamesapp.repositories.GamesRepository;
import com.devmountain.gamesapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService{

    @Autowired
    private FavoritesRepository favoritesRepository;
    @Autowired
    private GamesRepository gamesRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addFavorites(GamesDto gamesDto, FavoritesDto favoritesDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Games> gamesOptional = gamesRepository.findGamesById(gamesDto.getId());
        Favorites favorites = new Favorites(favoritesDto);
        userOptional.ifPresent(favorites::setUser);
        favoritesRepository.saveAndFlush(favorites);
        }


    @Override
    @Transactional
    public void deleteFavoritesById(Long favoritesId) {
        Optional<Favorites> favoritesOptional = favoritesRepository.findById(favoritesId);
        favoritesOptional.ifPresent(favorites -> favoritesRepository.delete(favorites));
    }
}
