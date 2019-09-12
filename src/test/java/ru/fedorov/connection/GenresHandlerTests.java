package ru.fedorov.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenresHandlerTests {

    private GenresHandler genresHandler;

    @BeforeEach
    void init() {
        genresHandler = new GenresHandler();
    }

    @DisplayName("Return not null")
    @Test
    void notNull() {
        Assertions.assertNotNull(genresHandler.getGenres());
    }
    
}
