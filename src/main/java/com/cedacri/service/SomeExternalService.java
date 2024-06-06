package com.cedacri.service;

import com.cedacri.model.Movie;

import java.util.List;

public interface SomeExternalService {
    boolean addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    Movie getMovieById(Long id);

    boolean deleteMovie(Long id);

    List<Movie> getMovies();
}
