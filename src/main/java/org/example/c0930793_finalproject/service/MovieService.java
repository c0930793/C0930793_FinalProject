package org.example.c0930793_finalproject.service;


import org.example.c0930793_finalproject.model.Movie;
import org.example.c0930793_finalproject.repository.FavoriteMovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String apiKey;

    private final FavoriteMovieRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public MovieService(FavoriteMovieRepository repository) {
        this.repository = repository;
    }

    public List<Map<String, Object>> getTrendingMovies() {
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + apiKey;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("results");
    }

    public Map<String, Object> getMovieDetails(Long movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Map.class);
    }

    public void addFavorite(Movie movie) {
        if (!repository.existsByMovieId(movie.getMovieId())) {
            repository.save(movie);
        }
    }

    public List<Movie> getFavorites() {
        return repository.findAll();
    }

    public void removeFavorite(Long id) {
        repository.deleteById(id);
    }
}