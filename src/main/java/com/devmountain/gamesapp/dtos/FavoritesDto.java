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
public class FavoritesDto implements Serializable {

    private Long id;

    private String name;

    private UserDto userDto;

    private Set<GamesDto> gamesDtoSet = new HashSet<>();

    public FavoritesDto(Favorites favorites){
        if (favorites.getId() != null){
            this.id = favorites.getId();
        }
    }

}
