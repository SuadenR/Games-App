package com.devmountain.gamesapp.controllers;

import com.devmountain.gamesapp.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @DeleteMapping("{/favoritesId}")
    public void deleteFavoritesById(@PathVariable Long favoritesId) { favoritesService.deleteFavoritesById(favoritesId);}
}
