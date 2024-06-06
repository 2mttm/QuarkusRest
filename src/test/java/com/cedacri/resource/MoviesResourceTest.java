package com.cedacri.resource;

import com.cedacri.model.Movie;
import com.cedacri.service.BestMoviesService;
import com.cedacri.service.SomeExternalService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@QuarkusTest
class MoviesResourceTest {
    @Inject
    SomeExternalService externalService;
    @Inject
    BestMoviesService bestMoviesService;

    List<Movie> movies = Arrays.asList(
            new Movie(0L, "Titanic", "James Cameron", 7.9),
            new Movie(1L, "The Matrix", "The Wachowskis", 8.7),
            new Movie(2L, "Interstellar", "Christopher Nolan", 8.8)
    );

    @Test
    public void getMovies() {
        assertEquals(externalService.getMovies(), movies);
        assertEquals(externalService.getMovies(), movies);
    }

    @Test
    public void getMovieById() {
        assertEquals("Titanic", externalService.getMovieById(0L).getTitle());
        assertEquals("The Matrix", externalService.getMovieById(1L).getTitle());
    }

    @Test
    public void getBestMovies() {
        assertEquals(3, bestMoviesService.findBestMovies(3.0).size());
        assertEquals(2, bestMoviesService.findBestMovies(8.0).size());
        assertEquals(1, bestMoviesService.findBestMovies(8.8).size());
    }

    @Test
    public void errorTest(){
        assertThrows(Exception.class, () -> externalService.getMovieById(999L));
    }

    @Test
    public void integrationRestTest() {
        given()
                .when().get("/movies/best")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void mockServiceTest() {
        SomeExternalService spyExternalService = spy(externalService);
        doReturn(Arrays.asList(
                new Movie(0L, "Titanic", "James Cameron", 7.9),
                new Movie(1L, "The Matrix", "The Wachowskis", 8.7)
        )).when(spyExternalService).getMovies();

        assertEquals(2, spyExternalService.getMovies().size());
        assertNotNull(spyExternalService.getMovieById(2L));
    }

}