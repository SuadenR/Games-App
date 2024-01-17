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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService{

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GamesRepository gamesRepository;

    @Override
    @Transactional
    public void addFavoritesToUser(FavoritesDto favoritesDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Favorites favorites = new Favorites(favoritesDto);
        userOptional.ifPresent(favorites::setUser);
        favoritesRepository.saveAndFlush(favorites);
        }

    @Override
    @Transactional
    public void deleteFavorites(Long favoritesId) {
        Optional<Favorites> favoritesOptional = favoritesRepository.findById(favoritesId);
        favoritesOptional.ifPresent(favorites -> favoritesRepository.delete(favorites));

    }

    @Override
    public Optional<FavoritesDto> getFavoritesById(Long favoritesId) {
        Optional<Favorites> favoritesOptional = favoritesRepository.findById(favoritesId);
        return favoritesOptional.map(FavoritesDto::new);
    }

    @Override
    public List<FavoritesDto> getAllFavorites() {
        List<Favorites> favoritesList = favoritesRepository.findAll();
        return  favoritesList.stream().map(FavoritesDto::new).collect(Collectors.toList());
    }

    @Override
    public List<GamesDto> getAllGamesFromFavoriteByUserId(Long userId) {

        Optional<Favorites> favoritesOptional = favoritesRepository.findFavoritesByUserId(userId);
        if (favoritesOptional.isPresent()){
            List<Games> gamesList = gamesRepository.findAllByFavoritesSetId(favoritesOptional.get().getId());
            return gamesList.stream().map(GamesDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
