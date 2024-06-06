package com.cedacri.service;

import com.cedacri.model.Movie;
import com.cedacri.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class SomeExternalServiceImpl implements SomeExternalService {
    private final MovieRepository movieRepository;

    @Inject
    public SomeExternalServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.updateMovie(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }

    @Override
    public boolean deleteMovie(Long id) {
        return movieRepository.deleteMovie(id);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.getMovies();
    }
}
