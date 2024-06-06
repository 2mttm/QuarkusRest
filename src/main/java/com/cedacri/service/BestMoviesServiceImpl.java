package com.cedacri.service;

import com.cedacri.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BestMoviesServiceImpl implements BestMoviesService {
    private final SomeExternalService externalService;

    @Inject
    public BestMoviesServiceImpl(SomeExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public List<Movie> findBestMovies(double minRating) {
        return externalService.getMovies().stream()
                .filter(movie -> movie.getRating() >= minRating)
                .toList();
    }
}
