package ru.fedorov.model.loyaltyplant.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;

import java.util.List;

public class PagesHandlerTests {

    private FilmsInfoHolder pagesHandler;

    @BeforeEach
    void init() {
        pagesHandler = new FilmsInfoHolder();
    }

    @DisplayName("Return not null")
    @Test
    void getAverageVoteNextPagesNotNull() {
        Assertions.assertNotNull(pagesHandler.getNextPages(1));
    }

    @DisplayName("Changes field")
    @Test
    void getAverageVoteNextPagesChangesField() {
        if (pagesHandler.getMaxPage() > 1) {
            Assertions.assertEquals(1, pagesHandler.getCurrentPage());
            pagesHandler.getNextPages(1);
            Assertions.assertEquals( 2, pagesHandler.getCurrentPage());
        }
    }

    @DisplayName("classTest")
    @Test
    void classTest() {
        if (pagesHandler.getMaxPage() > 2) {
            Assertions.assertTrue(pagesHandler.hasNext());
            Assertions.assertEquals(1, pagesHandler.getCurrentPage());

            pagesHandler.getNextPages(-1);

            Assertions.assertEquals(1, pagesHandler.getCurrentPage());

            pagesHandler.getNextPages(1);

            Assertions.assertEquals(2, pagesHandler.getCurrentPage());

            List<FilmInfo> averageVotes = pagesHandler.getNextPages(0);

            Assertions.assertEquals(pagesHandler.getMaxPage(),  pagesHandler.getCurrentPage());
            Assertions.assertFalse(pagesHandler.hasNext());
            Assertions.assertEquals(0, pagesHandler.getNextPages(1).size());
        }
    }
}
