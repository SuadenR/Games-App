package com.devmountain.gamesapp.repositories;


import com.devmountain.gamesapp.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    Optional<Favorites> findFavoritesByNameAndUserId(Long gamesId, String favorites, Long userId);
}
