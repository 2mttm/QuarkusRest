package com.cedacri.repository;

import com.cedacri.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie(0L, "Titanic", "James Cameron", 7.9),
            new Movie(1L, "The Matrix", "The Wachowskis", 8.7),
            new Movie(2L, "Interstellar", "Christopher Nolan", 8.8),
            new Movie(3L, "Inception", "Christopher Nolan", 5.9),
            new Movie(4L, "The Shawshank Redemption", "Frank Darabont", 9.3),
            new Movie(5L, "Pulp Fiction", "Quentin Tarantino", 2.9),
            new Movie(6L, "Forrest Gump", "Robert Zemeckis", 8.8),
            new Movie(7L, "The Dark Knight", "Christopher Nolan", 9.0),
            new Movie(8L, "Fight Club", "David Fincher", 3.8),
            new Movie(9L, "The Godfather", "Francis Ford Coppola", 6.2),
            new Movie(10L, "The Lion King", "Roger Allers, Rob Minkoff", 8.5)
    ));

    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    public Movie updateMovie(Movie movie) {
        Optional<Movie> existingMovie = movies.stream()
                .filter(m -> m.getId().equals(movie.getId()))
                .findFirst();

        if (existingMovie.isPresent()) {
            existingMovie.get().setTitle(movie.getTitle());
            existingMovie.get().setDirector(movie.getDirector());
            existingMovie.get().setRating(movie.getRating());

            return existingMovie.get();
        } else {
            throw new NoSuchElementException("Movie not found");
        }
    }

    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public boolean deleteMovie(Long id) {
        return movies.removeIf(movie -> id.equals(movie.getId()));
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
