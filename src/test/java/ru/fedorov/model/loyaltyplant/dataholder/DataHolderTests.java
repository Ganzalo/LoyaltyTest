package ru.fedorov.model.loyaltyplant.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;

import java.util.*;

public class DataHolderTests {

//    DataHolder dataHolder;
//
//    @BeforeEach
//    void init() {
//        dataHolder = new DataHolder();
//    }
//
//    @DisplayName("Return not null")
//    @Test
//    void getGenresNotNull() {
//        Assertions.assertNotNull(dataHolder.getGenres());
//    }
//
//    @DisplayName("Return unmodifiableMap<Integer, String>")
//    @Test
//    void getGenresReturnClass() {
//        Map<Integer, String> genres = dataHolder.getGenres();
//        if (genres.size() > 1) {
//            Map<Integer, String> expect = Collections.unmodifiableMap(new HashMap<>());
//            Assertions.assertEquals(expect.getClass(), genres.getClass());
//            Assertions.assertEquals(expect.getClass().getName(), genres.getClass().getName());
//        }
//    }
//
//    @DisplayName("Max page")
//    @Test
//    void maxPageTest() {
//        Assertions.assertTrue(dataHolder.getMaxPage() > 0);
//    }
//
//    @DisplayName("getNextPages")
//    @Test
//    void getNextPagesNotNull() {
//        if (FilmsInfoHolder.requestMaxPage() > 1) {
//            Assertions.assertNotNull(dataHolder.getFilmsInfo(1));
//        }
//    }
//
//    @DisplayName("Return List<FilmInfo>")
//    @Test
//    void getNextPagesReturnClass() {
//        List<FilmInfo> films = dataHolder.getFilmsInfo(1);
//        if (FilmsInfoHolder.requestMaxPage() > 1) {
//            List<FilmInfo> expect = new ArrayList<>();
//            Assertions.assertEquals(expect.getClass(), films.getClass());
//            Assertions.assertEquals(expect.getClass().getName(), films.getClass().getName());
//        }
//    }
//
//    @DisplayName("Correct getCurrentPage")
//    @Test
//    void getCurrentPageChangeTest() {
//        if (FilmsInfoHolder.requestMaxPage() > 1) {
//            Assertions.assertEquals(1, dataHolder.getCurrentPage());
//            Assertions.assertNotNull(dataHolder.getFilmsInfo(1));
//            Assertions.assertEquals(2, dataHolder.getCurrentPage());
//        }
//    }
//
//    @DisplayName("Correct hasNextPage")
//    @Test
//    void hasNextPageTest() {
//        if (FilmsInfoHolder.requestMaxPage() > 1) {
//            Assertions.assertTrue(dataHolder.hasNextPage());
//        } else {
//            Assertions.assertFalse(dataHolder.hasNextPage());
//        }
//    }

}
