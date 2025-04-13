package org.example.c0930793_finalproject.repository;


import org.example.c0930793_finalproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByMovieId(Long movieId);
}
