package com.devmountain.gamesapp.entities;

import com.devmountain.gamesapp.dtos.FavoritesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @Column
    private String name;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "FavoriteGame",
            joinColumns = @JoinColumn(name = "favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Games> gamesSet = new HashSet<>();

    public Favorites(FavoritesDto favoritesDto){

        if (favoritesDto.getName() != null){
            this.name  = favoritesDto.getName();
        }

        }
    }

