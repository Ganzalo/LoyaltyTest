package ru.fedorov.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.Genre;
import ru.fedorov.model.Page;
import ru.fedorov.model.VoteAverage;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenresHandlerTests {

    private GenresHandler genresHandler;

    @BeforeEach
    void init() {
        genresHandler = new GenresHandler();
    }

    @DisplayName("Return not null")
    @Test
    void getGenresNotNull() {
        Assertions.assertNotNull(genresHandler.getGenres());
    }

    @DisplayName("Return List<Genre>")
    @Test
    void getGenresReturnClass() {
        List<Genre> result = genresHandler.getGenres();
        if(result.size() > 1) {
            List<Genre> expect = new ArrayList<>();
            expect.add(new Genre());
            Assertions.assertEquals(expect.getClass(), genresHandler.getGenres().getClass());
            Assertions.assertEquals(expect.getClass().getName(), genresHandler.getGenres().getClass().getName());
        }
    }

}
