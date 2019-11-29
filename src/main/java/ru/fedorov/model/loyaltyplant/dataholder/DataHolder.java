package ru.fedorov.model.loyaltyplant.dataholder;

import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.genres.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Фасад над классами, храняищмими данные для бизнес логики
 */

public class DataHolder {

    private FilmsInfoHolder pagesHolder;

    public DataHolder() {
        pagesHolder = new FilmsInfoHolder();
    }

    /**
     * Перобразует List<Genre> в Map<Integer, String> и оборачивает в unmodifiableMap
     *
     * @return Map<Integer, String> обернутую в unmodifiableMap
     */
    public Map<Integer, String> getGenres() {
        return Collections.unmodifiableMap(GenresHolder.requestGenres().stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getName)));
    }

    public int getCurrentPage() {
        return pagesHolder.getCurrentPage();
    }

    public int getMaxPage() {
        return pagesHolder.getMaxPage();
    }

    public List<FilmInfo> getNextPages(int pageStep) {
        return pagesHolder.getNextPages(pageStep);
    }

    public boolean hasNextPage() {
        return pagesHolder.hasNext();
    }
}
