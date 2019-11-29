package ru.fedorov.model.loyaltyplant.dataholder;

import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;

import java.util.List;
import java.util.Map;

/**
 * Фасад над классами, храняищмими данные для бизнес логики
 */

public class DataHolder {

    private FilmsInfoHolder pagesHolder;

    public DataHolder() {
        pagesHolder = new FilmsInfoHolder();
    }

    public Map<Integer, String> getGenres() {
        return GenresHolder.getGenres();
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
