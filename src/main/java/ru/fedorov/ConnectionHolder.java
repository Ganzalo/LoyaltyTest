package ru.fedorov;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.model.AverageVote;
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

    public List<AverageVote> getAverageVoteByPage(int currPage) {
        if(!hasNext()) {
            throw new IndexOutOfBoundsException("Превышен лимит страниц");
        }

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

        if(page == null)
            return Collections.emptyList();
        return new ArrayList<>(page.getResults());
    }

    public List<AverageVote> getAverageVoteNextPage(int pageCount) throws IOException {
        if(!hasNext(pageCount)) {
            throw new IndexOutOfBoundsException("Превышен лимит страниц");
        }

        List<AverageVote> pages = new ArrayList<>();
        int endPage = currPage + pageCount;

        while (currPage < endPage) {
            pages.addAll(getAverageVoteByPage(currPage));
            currPage++;
        }

        return new ArrayList<>(pages);
    }

    public boolean hasNext() {
        return this.maxPage > (this.currPage + 1) ;
    }

    public boolean hasNext(int countPage) {
        return this.maxPage > (this.currPage + countPage) ;
    }

}
