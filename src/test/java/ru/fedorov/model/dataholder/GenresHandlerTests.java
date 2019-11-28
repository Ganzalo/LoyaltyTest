package ru.fedorov.model.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenresHandlerTests {

    @DisplayName("Return not null")
    @Test
    void getGenresNotNull() {
        Assertions.assertNotNull(GenresHolder.getGenres());
    }

    @DisplayName("Return Map<Integer, String>")
    @Test
    void getGenresReturnClass() {
        Map<Integer, String> genres = GenresHolder.getGenres();
        if(genres.size() > 1) {
            Map<Integer, String> expect = Collections.unmodifiableMap(new HashMap<>());
            Assertions.assertEquals(expect.getClass(), GenresHolder.getGenres().getClass());
            Assertions.assertEquals(expect.getClass().getName(), GenresHolder.getGenres().getClass().getName());
        }
    }

}
