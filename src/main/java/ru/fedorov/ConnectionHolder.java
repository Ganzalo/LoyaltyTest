package ru.fedorov;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.model.VoteAverage;
import ru.fedorov.model.Page;
import ru.fedorov.model.Genre;
import ru.fedorov.model.Genres;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionHolder {

    private final String SITE = "https://easy.test-assignment-a.loyaltyplant.net";
    private final String KEY = "72b56103e43843412a992a8d64bf96e9";
    private final String REQUEST_GENRES = SITE + "/3/genre/movie/list?api_key=" + KEY;
    private final String REQUEST_AVERAGE_VOTE = SITE + "/3/discover/movie?api_key=" + KEY + "&page=";

    private int currPage = 1;
    private final int maxPage = maxPage();

    public ConnectionHolder() {
    }

    private int maxPage() {
        int maxPages = 0;
        String link = REQUEST_AVERAGE_VOTE + currPage;
        ObjectNode node = null;
        try {
            URL urlMaxPage = new URL(link);

            HttpURLConnection connection = (HttpURLConnection) urlMaxPage.openConnection();
            connection.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readValue(urlMaxPage, ObjectNode.class);
        } catch (IOException e) {
            System.out.println("Ошибка получения значения максимальной страницы");
            e.printStackTrace();
        }

        if (node.has("total_pages")) {
            maxPages = node.get("total_pages").asInt();
        }

        return maxPages;
    }

    public List<Genre> getListGenre() throws IOException {
        URL urlGenre = new URL(REQUEST_GENRES);
        HttpURLConnection connection = (HttpURLConnection) urlGenre.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        Genres genres = mapper.readValue(urlGenre, new TypeReference<Genres>() {});

        return new ArrayList<>(genres.getGenres());
    }

    /**
     * Получает лист объектов AverageVote с каждой страницы начиная с currPage вплоть пока
     * не будет выполнено #getAverageVoteByPage() pageCount раз. Если pageCount + currPage
     * первышает maxPage то будет взято AverageVote с максимального кол-во страниц.
     *
     * @param pageCount кол-во страниц с которых будет взять объекты AverageVote. Если
     *                  pageCount = 0  то запрос будет сделанн для всех страниц. Если
     *                  pageCount < 0 то Collections.emptyList()
     * @return List<AverageVote> если еще есть страницы, иначе Collections.emptyList()
     * @see #getAverageVoteByPage()
     */

    public List<VoteAverage> getAverageVoteNextPages(int pageCount) {
        if (!hasNext() || pageCount < 0) {
            return Collections.emptyList();
        }

        if (pageCount == 0)
            pageCount = maxPage;

        int endPage = this.currPage + pageCount;

        if (endPage > this.maxPage)
            endPage = this.maxPage;

        List<VoteAverage> pages = new ArrayList<>();
        while (this.currPage < endPage) {
            pages.addAll(getAverageVoteByPage());
            this.currPage++;
        }

        return new ArrayList<>(pages);
    }

    public boolean hasNext() {
        return this.maxPage > (this.currPage) ;
    }

    private List<VoteAverage> getAverageVoteByPage() {
        Page page = null;
        try {
            URL urlAverageVotes = new URL(REQUEST_AVERAGE_VOTE + this.currPage);

            HttpURLConnection connection = (HttpURLConnection) urlAverageVotes.openConnection();
            connection.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            page = mapper.readValue(urlAverageVotes, new TypeReference<Page>() {});
        } catch (IOException e) {
            System.out.println("Ошибка получения страницы");
            e.printStackTrace();
        }

        if (page == null) {
            System.out.println("Пропуск страницы" + currPage);
            return new ArrayList<>();
        }

        return new ArrayList<>(page.getResults());
    }
}
