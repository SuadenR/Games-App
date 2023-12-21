package com.devmountain.gamesapp.controllers;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @PostMapping("/add")
    public void addGames(@RequestBody GamesDto gamesDto){ gamesService.addGames(gamesDto);}

    @DeleteMapping("/{gamesId}")
    public void deleteGamesById(@PathVariable Long gamesId){ gamesService.deleteGamesById(gamesId);}

    @GetMapping("/{gamesId}")
    public Optional<GamesDto> findGamesById(@PathVariable Long gamesId){ return gamesService.findGamesById(gamesId);}

    @PutMapping("/update")
    public Optional<Games> updateGames(@RequestBody GamesDto gamesDto){ return gamesService.updateGames(gamesDto);}

}
