package ru.fedorov.model.loyaltyplant.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.loyaltyplant.vo.genres.Genre;

import java.util.*;

public class GenresHandlerTests {

    @DisplayName("Return not null")
    @Test
    void getGenresNotNull() {
        Assertions.assertNotNull(GenresHolder.requestGenres());
    }

    @DisplayName("Return Map<Integer, String>")
    @Test
    void getGenresReturnClass() {
        List<Genre> genres = GenresHolder.requestGenres();
        if(genres.size() > 1) {
            List<Genre> expect = new ArrayList<>();
            Assertions.assertEquals(expect.getClass(), genres.getClass());
            Assertions.assertEquals(expect.getClass().getName(), genres.getClass().getName());
        }
    }

}
