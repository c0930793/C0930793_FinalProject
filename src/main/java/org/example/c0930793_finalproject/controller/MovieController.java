package org.example.c0930793_finalproject.controller;

import org.example.c0930793_finalproject.model.Movie;
import org.example.c0930793_finalproject.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String home(Model model) {
        List<Map<String, Object>> movies = movieService.getTrendingMovies();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieDetails(id));
        return "details";
    }

    @PostMapping("/favorite")
    public String addFavorite(@ModelAttribute Movie movie) {
        movieService.addFavorite(movie);
        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        model.addAttribute("movies", movieService.getFavorites());
        return "favorites";
    }

    @PostMapping("/favorite/remove/{id}")
    public String removeFavorite(@PathVariable Long id) {
        movieService.removeFavorite(id);
        return "redirect:/favorites";
    }
}