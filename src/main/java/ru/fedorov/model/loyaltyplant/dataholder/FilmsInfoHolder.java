package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.Console;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_AVERAGE_VOTE;

/**
 * Получение информации о фильмах
 * работа класса напоминает итератор
 */

class FilmsInfoHolder {

    /**
     * текущая страница фильмов
     */
    private int currentPage = 1;
    /**
     * максимальная страница фильмов
     */
    private final int maxPage = maxPage();

    int getCurrentPage() {
        return currentPage;
    }

    int getMaxPage() {
        return maxPage;
    }

    /**
     * Получение значения максимальной страницы
     */
    private int maxPage() {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + currentPage), ObjectNode.class);
        } catch (IOException e) {
            Console.writeMessage("Ошибка получения значения максимальной страницы");
        }

        if (node == null)
            return 0;

        int maxPages = 0;
        if (node.has("total_pages")) {
            maxPages = node.get("total_pages").asInt();
        }

        return maxPages;
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
     * @see #getPage()
     */

    List<FilmInfo> getNextPages(int pageStep) {
        if (!hasNext() || pageStep < 0)
            return Collections.emptyList();

        if (pageStep == 0)
            pageStep = maxPage;

        int endPage = this.currentPage + pageStep;

        if (endPage > this.maxPage)
            endPage = this.maxPage;

        List<FilmInfo> pages = new ArrayList<>();
        while (this.currentPage < endPage) {
            pages.addAll(getPage());
            this.currentPage++;
        }

        return new ArrayList<>(pages);
    }

    /**
     * Проверяет наличие следующий странциы
     */
    boolean hasNext() {
        return this.maxPage > this.currentPage;
    }

    /**
     * Получает лист объектов Page за страницу по endpoint c помощью currentPage
     */
    private List<FilmInfo> getPage() {
        Page page = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            page = mapper.readValue(new URL(REQUEST_AVERAGE_VOTE + this.currentPage),
                    new TypeReference<Page>() {});
        } catch (IOException e) {
            Console.writeMessage("Ошибка получения страницы ");
        }

        if (page == null) {
            Console.writeMessage("Пропуск страницы " + currentPage);
            return Collections.emptyList();
        }

        return new ArrayList<>(page.getResults());
    }
}
