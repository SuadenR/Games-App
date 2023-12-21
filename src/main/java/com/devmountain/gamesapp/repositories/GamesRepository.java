package com.devmountain.gamesapp.repositories;


import com.devmountain.gamesapp.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {
    Optional<Games> findGamesById(Long gamesId);

}
