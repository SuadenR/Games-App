package com.devmountain.gamesapp.repositories;

import com.devmountain.gamesapp.entities.Games;
import jakarta.persistence.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {
//    Optional<Games> findByGameTitle(String gameTitle);
//    Optional<Games> findByPublisher(String publisher);
//    Optional<Games> findByGenre(String genre);
//    Optional<Games> findByPlatform(String platform);
//    Optional<Games> findByRating();

}
