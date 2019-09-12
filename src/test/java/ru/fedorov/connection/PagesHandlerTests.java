package ru.fedorov.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.VoteAverage;

import java.util.List;

public class PagesHandlerTests {

    private PagesHandler pagesHandler;

    @BeforeEach
    void init() {
        pagesHandler = new PagesHandler();
    }

    @DisplayName("Return not null")
    @Test
    void getAverageVoteNextPagesNotNull() {
        Assertions.assertNotNull(pagesHandler.getAverageVoteNextPages(1));
    }

    @DisplayName("Changes field")
    @Test
    void getAverageVoteNextPagesChangesField() {
        if (pagesHandler.getMaxPage() > 1) {
            Assertions.assertEquals(1, pagesHandler.getCurrentPage());
            pagesHandler.getAverageVoteNextPages(1);
            Assertions.assertEquals( 2, pagesHandler.getCurrentPage());
        }
    }

    @DisplayName("classTest")
    @Test
    void classTest() {
        if (pagesHandler.getMaxPage() > 2) {
            Assertions.assertTrue(pagesHandler.hasNext());
            Assertions.assertEquals(1, pagesHandler.getCurrentPage());

            pagesHandler.getAverageVoteNextPages(-1);

            Assertions.assertEquals(1, pagesHandler.getCurrentPage());

            pagesHandler.getAverageVoteNextPages(1);

            Assertions.assertEquals(2, pagesHandler.getCurrentPage());

            List<VoteAverage> averageVotes = pagesHandler.getAverageVoteNextPages(0);

            Assertions.assertEquals(pagesHandler.getMaxPage(),  pagesHandler.getCurrentPage());
            Assertions.assertFalse(pagesHandler.hasNext());
            Assertions.assertEquals(0, pagesHandler.getAverageVoteNextPages(1).size());
        }
    }
}
