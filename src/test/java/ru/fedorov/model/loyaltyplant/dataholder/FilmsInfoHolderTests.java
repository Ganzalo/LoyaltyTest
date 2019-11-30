package ru.fedorov.model.loyaltyplant.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;

import java.util.ArrayList;
import java.util.List;

public class FilmsInfoHolderTests {

    @DisplayName("maxPage")
    @Test
    void requestMaxPageTest() {
        Assertions.assertTrue(FilmsInfoHolder.requestMaxPage() > 0);
    }

    @DisplayName("getPage")
    @Test
    void requestPageTest() {
        if (FilmsInfoHolder.requestMaxPage() > 1) {
            Assertions.assertNotNull(FilmsInfoHolder.requestPage(1));
        }
    }

    @DisplayName("Return List<FilmInfo>")
    @Test
    void requestFilmInfoReturnClass() {
        List<FilmInfo> films = FilmsInfoHolder.requestPage(1);
        if (FilmsInfoHolder.requestMaxPage() > 1) {
            List<FilmInfo> expect = new ArrayList<>();
            Assertions.assertEquals(expect.getClass(), films.getClass());
            Assertions.assertEquals(expect.getClass().getName(), films.getClass().getName());
        }
    }
}
