package com.cedacri.resource;

import com.cedacri.model.Movie;
import com.cedacri.service.BestMoviesService;
import com.cedacri.service.SomeExternalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response getMovies() {
        return Response.ok(externalService.getMovies()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") Long id) {
        Movie movie = externalService.getMovieById(id);
        return movie == null ? Response.noContent().build() : Response.ok(movie).build();
    }

    @GET
    @Path("/best/{minRating}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestMovies(@PathParam("minRating") double minRating) {
        return Response.ok(bestMoviesService.findBestMovies(minRating)).build();
    }

    @GET
    @Path("/best")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestMovies() {
        List<Movie> movies = bestMoviesService.findBestMovies(8.0);
        return !movies.isEmpty() ? Response.ok(movies).build() : Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        return externalService.deleteMovie(id) ? Response.ok().build() : Response.notModified().build();
    }
}
