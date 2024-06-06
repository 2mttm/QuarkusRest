package com.cedacri.service;

import com.cedacri.model.Movie;

import java.util.List;

public interface BestMoviesService {
    List<Movie> findBestMovies(double minRating);
}
