package ru.fedorov.model.loyaltyplant.dataholder;

import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.genres.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Фасад над классами, храняищмими данные для бизнес логики
 */
public class DataHolder {

    /**
     * текущая страница фильмов
     */
    private int currentPage = 1;
    /**
     * максимальная страница фильмов
     */
    private final int maxPage = FilmsInfoHolder.maxPage();

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getMaxPage() {
        return this.maxPage;
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

    /**
     * Получает лист объектов Page с каждой страницы начиная с currentPage вплоть пока
     * не будет выполнено #getAverageVoteByPage() pageStep раз. Если pageStep + currPage
     * первышает maxPage то будет взято AverageVote с максимального кол-ва страниц.
     *
     * @param pageStep кол-во страниц с которых будут получены объекты Page. Если
     *                 pageStep = 0  то запрос будет сделан для всех страниц. Если
     *                 pageStep < 0 то Collections.emptyList()
     * @return List<Page> если еще есть страницы, иначе Collections.emptyList()
     */
    public List<FilmInfo> getNextPages(int pageStep) {
        if (!hasNextPage() || pageStep < 0)
            return Collections.emptyList();

        if (pageStep == 0)
            pageStep = this.maxPage;

        int endPage = this.currentPage + pageStep;

        if (endPage > this.maxPage)
            endPage = this.maxPage;

        List<FilmInfo> pages = new ArrayList<>();
        while (this.currentPage < endPage) {
            pages.addAll(FilmsInfoHolder.getPage(this.currentPage));
            this.currentPage++;
        }

        return new ArrayList<>(pages);
    }

    /**
     * Проверяет наличие следующий странциы
     */
    public boolean hasNextPage() {
        return this.maxPage > this.currentPage;
    }
}
