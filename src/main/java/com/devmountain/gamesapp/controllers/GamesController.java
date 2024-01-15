package com.devmountain.gamesapp.controllers;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @PostMapping("/add")
    public void addGames(@RequestBody GamesDto gamesDto){ gamesService.addGames(gamesDto);}

    @DeleteMapping("/{gamesId}")
    public void deleteGamesById(@PathVariable Long gamesId){ gamesService.deleteGamesById(gamesId);}

    @GetMapping("/{gamesId}")
    public Optional<GamesDto> findGamesById(@PathVariable Long gamesId){ return gamesService.findGamesById(gamesId);}

    @GetMapping("/")
    public List<GamesDto> getAllGames() { return gamesService.getAllGames();}

    @PutMapping
    public void updateGames(@RequestBody GamesDto gamesDto){ gamesService.updateGames(gamesDto);}

    @PostMapping("/favorites/add/{userId}/{gamesId}/{favoritesId}")
    public  String addGamesToFavorites(@PathVariable Long gamesId, @PathVariable Long favoritesId, @PathVariable Long userId) { return gamesService.addGamesToFavorites(gamesId, favoritesId, userId);}

    @PostMapping("/favorites/add/{userId}/{gamesId}")
    public  String addGamesToFavorites(@PathVariable Long gamesId, @PathVariable Long userId) { return gamesService.addGamesToFavorites(gamesId, userId);}

}
