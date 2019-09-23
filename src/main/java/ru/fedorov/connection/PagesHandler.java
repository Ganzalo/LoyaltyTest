package ru.fedorov.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.model.AverageVote;
import ru.fedorov.model.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.connection.Consts.REQUEST_AVERAGE_VOTE;

public class PagesHandler {

    private int currentPage = 1900;
    private final int maxPage = maxPage();

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    private int maxPage() {
        ObjectNode node = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            node = mapper.readValue(new URL(REQUEST_AVERAGE_VOTE + currentPage), ObjectNode.class);
        } catch (IOException e) {
            System.out.println("Ошибка получения значения максимальной страницы ");
            e.printStackTrace();
        }

        int maxPages = 0;
        if (node == null) {
            return maxPages;
        }

        if (node.has("total_pages")) {
            maxPages = node.get("total_pages").asInt();
        }

        return maxPages;
    }

    /**
     * Получает лист объектов AverageVote с каждой страницы начиная с currentPage вплоть пока
     * не будет выполнено #getAverageVoteByPage() pageStep раз. Если pageStep + currPage
     * первышает maxPage то будет взято AverageVote с максимального кол-ва страниц.
     *
     * @param pageStep кол-во страниц с которых будут получены объекты AverageVote. Если
     *                  pageStep = 0  то запрос будет сделан для всех страниц. Если
     *                  pageStep < 0 то Collections.emptyList()
     * @return List<AverageVote> если еще есть страницы, иначе Collections.emptyList()
     * @see #getAverageVoteByPage()
     */

    public List<AverageVote> getAverageVotesNextPages(int pageStep) {
        if (!hasNext() || pageStep < 0) {
            return Collections.emptyList();
        }

        if (pageStep == 0)
            pageStep = maxPage;

        int endPage = this.currentPage + pageStep;

        if (endPage > this.maxPage)
            endPage = this.maxPage;

        List<AverageVote> pages = new ArrayList<>();
        while (this.currentPage < endPage) {
            pages.addAll(getAverageVoteByPage());
            this.currentPage++;
        }
        return new ArrayList<>(pages);
    }

    public boolean hasNext() {
        return this.maxPage > this.currentPage;
    }

    private List<AverageVote> getAverageVoteByPage() {
        Page page = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            page = mapper.readValue(new URL(REQUEST_AVERAGE_VOTE + this.currentPage),
                                    new TypeReference<Page>() {});
        } catch (IOException e) {
            System.out.println("Ошибка получения страницы ");
            e.printStackTrace();
        }

        if (page == null) {
            System.out.println("Пропуск страницы " + currentPage);
            return Collections.emptyList();
        }

        return new ArrayList<>(page.getResults());
    }
}
