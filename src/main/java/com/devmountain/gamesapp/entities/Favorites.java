package com.devmountain.gamesapp.entities;

import com.devmountain.gamesapp.dtos.FavoritesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@Table(name = "Favorites")
@NoArgsConstructor
@AllArgsConstructor
public class Favorites{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Favorites favorites;

    @ManyToMany(mappedBy = "Games", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Games> gamesSet = new HashSet<>();

    public Favorites(FavoritesDto favoritesDto){
        if(favoritesDto.getFavorites() != null){
            this.favorites = favoritesDto.getFavorites();
        }
        if (favoritesDto.getGamesSet() != null){
            this.gamesSet = favoritesDto.getGamesSet();
        }
    }
}
