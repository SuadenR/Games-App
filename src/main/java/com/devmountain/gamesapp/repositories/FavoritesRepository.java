package com.devmountain.gamesapp.repositories;


import com.devmountain.gamesapp.entities.Favorites;
import com.devmountain.gamesapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    Optional<Favorites> findFavoritesByNameAndUserId (String favoritesName, Long userId);


    List<Favorites> findAllByUser(User user);
}
