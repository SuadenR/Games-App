package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.FavoritesDto;
import com.devmountain.gamesapp.entities.Favorites;
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
    public List<FavoritesDto> getFavoritesByUserId(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Favorites> favoritesList = favoritesRepository.findAllByUser(userOptional.get());
            return favoritesList.stream().map(favorites -> new FavoritesDto(favorites)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<FavoritesDto> getAllFavorites() {
        List<Favorites> favoritesList = favoritesRepository.findAll();
        return  favoritesList.stream().map(favorites -> new FavoritesDto(favorites)).collect(Collectors.toList());
    }
}
