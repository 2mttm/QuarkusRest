package com.cedacri.resource;

import com.cedacri.model.Movie;
import com.cedacri.service.BestMoviesService;
import com.cedacri.service.SomeExternalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/movies")
public class MoviesResource {
    private final SomeExternalService externalService;
    private final BestMoviesService bestMoviesService;

    @Inject
    public MoviesResource(SomeExternalService externalService, BestMoviesService bestMoviesService) {
        this.externalService = externalService;
        this.bestMoviesService = bestMoviesService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return externalService.getMovies();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getMovie(@PathParam("id") Long id) {
        return externalService.getMovieById(id);
    }

    @GET
    @Path("/best/{minRating}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getBestMovies(@PathParam("minRating") double minRating) {
        return bestMoviesService.findBestMovies(minRating);
    }

    @GET
    @Path("/best")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getBestMovies() {
        return getBestMovies(8.0);
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteMovie(@PathParam("id") Long id) {
        return externalService.deleteMovie(id);
    }
}
