package com.devmountain.gamesapp.entities;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Games")
@NoArgsConstructor
@AllArgsConstructor
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String gameTitle;

    @Column
    private String publisher;

    @Column
    private String genre;

    @Column
    private String platform;

    @Column
    @Enumerated(EnumType.STRING) // To store the enum as a string in the database
    private EsrbRating rating;


    @ManyToMany(mappedBy = "gamesSet", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Favorites> favoritesSet = new HashSet<>();

    public Games(GamesDto gamesDto){
        if(gamesDto.getId() != null){
            this.id = gamesDto.getId();
        }
        if(gamesDto.getGameTitle() != null){
            this.gameTitle = gamesDto.getGameTitle();
        }
        if(gamesDto.getPublisher() != null){
            this.publisher = gamesDto.getPublisher();
        }
        if(gamesDto.getGenre() != null){
            this.genre = gamesDto.getGenre();
        }
        if(gamesDto.getRating() != null){
            this.rating = EsrbRating.valueOf(gamesDto.getRating());
        }
        if(gamesDto.getPlatform() != null){
            this.platform = gamesDto.getPlatform();
        }
    }

}

