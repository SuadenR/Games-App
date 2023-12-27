package com.devmountain.gamesapp.dtos;

import com.devmountain.gamesapp.entities.Favorites;
import com.devmountain.gamesapp.entities.Games;
import com.devmountain.gamesapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesDto implements Serializable {

    private Long id;

    private User user;

    private String gameTitle;

    private String publisher;

    private String genre;

    private String platform;

    private String rating;

    private Games games;

    private Set<FavoritesDto> favoritesDtoSet = new HashSet<>();

    public GamesDto(Games games){
        if (games.getId() != null){
            this.id = games.getId();
        }
        if (games.getGameTitle() != null){
            this.gameTitle = games.getGameTitle();
        }
        if (games.getPublisher() != null){
            this.publisher = games.getPublisher();
        }
        if (games.getGenre() != null){
            this.genre = games.getGenre();
        }
        if (games.getRating() != null){
            this.rating = games.getRating();
        }
        if (games.getPlatform() != null){
            this.platform = games.getPlatform();
        }
    }
}
