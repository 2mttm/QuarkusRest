package com.cedacri.resource;

import com.cedacri.model.Movie;
import com.cedacri.service.BestMoviesService;
import com.cedacri.service.SomeExternalService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.annotations.Message;
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
            new Movie(2L, "Interstellar", "Christopher Nolan", 8.8),
            new Movie(3L, "Inception", "Christopher Nolan", 5.9),
            new Movie(4L, "The Shawshank Redemption", "Frank Darabont", 9.3),
            new Movie(5L, "Pulp Fiction", "Quentin Tarantino", 2.9),
            new Movie(6L, "Forrest Gump", "Robert Zemeckis", 8.8),
            new Movie(7L, "The Dark Knight", "Christopher Nolan", 9.0),
            new Movie(8L, "Fight Club", "David Fincher", 3.8),
            new Movie(9L, "The Godfather", "Francis Ford Coppola", 6.2),
            new Movie(10L, "The Lion King", "Roger Allers, Rob Minkoff", 8.5)
    );

    @Test
    @Message("serviciul extern intoarece acelasi raspuns pentru orice chemare")
    public void getMovies() {
        assertEquals(externalService.getMovies(), movies);
        assertEquals(externalService.getMovies(), movies);
    }

    @Test
    @Message("serviciul extern intoarce raspusnuri in functie de valoarea la parametri")
    public void getMovieById() {
        assertEquals("Titanic", externalService.getMovieById(0L).getTitle());
        assertEquals("The Matrix", externalService.getMovieById(1L).getTitle());
    }

    @Test
    @Message("serviciul extern intoarce raspusnuri in functie de valoarea la parametri")
    public void getBestMovies() {
        assertEquals(8, bestMoviesService.findBestMovies(6.0).size());
        assertEquals(6, bestMoviesService.findBestMovies(8.0).size());
        assertEquals(2, bestMoviesService.findBestMovies(9.0).size());
    }

    @Test
    @Message("serviciul extern semnaleaza o careva eroare")
    public void errorTest(){
        assertThrows(Exception.class, () -> externalService.getMovieById(999L));
    }

    @Test
    public void integrationRestTest() {
        given()
                .when()
                .get("/movies/best")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Message("serviciul extern intoarce lista de valori definite in clasa SomeExternalService, cu exceptia la un test, unde se va crea un mock")
    public void mockServiceTest() {
        SomeExternalService spyExternalService = spy(externalService);

        doReturn(Arrays.asList(
                new Movie(0L, "Titanic", "James Cameron", 7.9),
                new Movie(1L, "The Matrix", "The Wachowskis", 8.7)
        )).when(spyExternalService).getMovies();

        assertEquals(2, spyExternalService.getMovies().size());
        assertNotNull(spyExternalService.getMovieById(5L));
    }

    @Test
    @Message("serviciul extern intoarce raspunsuri diferite pentru chemari consecutive")
    public void consecutiveCallsTest(){
        SomeExternalService spyExternalService = spy(externalService);

        doReturn(Arrays.asList(
                new Movie(0L, "Titanic", "James Cameron", 7.9),
                new Movie(1L, "The Matrix", "The Wachowskis", 8.7)
        ), Arrays.asList(
                new Movie(0L, "Titanic", "James Cameron", 7.9)
        )).when(spyExternalService).getMovies();

        assertEquals(2, spyExternalService.getMovies().size());
        assertEquals(1, spyExternalService.getMovies().size());
    }

}