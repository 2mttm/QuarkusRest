package com.cedacri.service;

import com.cedacri.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class SomeExternalServiceImpl implements SomeExternalService {
    private List<Movie> movies = new ArrayList<>(Arrays.asList(
        new Movie(0L, "Titanic", "James Cameron", 7.9),
        new Movie(1L, "The Matrix", "The Wachowskis", 8.7),
        new Movie(2L, "Interstellar", "Christopher Nolan", 8.8)
    ));

    @Override
    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movies.stream().filter(movie -> movie.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public boolean deleteMovie(Long id) {
        return movies.removeIf(movie -> id.equals(movie.getId()));
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }
}
