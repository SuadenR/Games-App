package com.devmountain.gamesapp.controllers;

import com.devmountain.gamesapp.dtos.FavoritesDto;
import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/")
    public List<FavoritesDto> getAllFavorites() { return favoritesService.getAllFavorites();}

    @PostMapping("/add/{userId}")
    public void addFavoritesToUser(@RequestBody FavoritesDto favoritesDto, @PathVariable Long userId){ favoritesService.addFavoritesToUser(favoritesDto, userId);}

    @DeleteMapping("/{favoritesId}")
    public void deleteFavorites(@PathVariable Long favoritesId) { favoritesService.deleteFavorites(favoritesId);}

//    @GetMapping("/{userId}")
//    public List<FavoritesDto> getFavoritesByUserId(@PathVariable Long userId) { return favoritesService.getFavoritesByUserId(userId);}

    @GetMapping("/games/{userId}")
    public List<GamesDto> getFavoritesByUserId(@PathVariable Long userId) { return favoritesService.getAllGamesFromFavoriteByUserId(userId);}

}
