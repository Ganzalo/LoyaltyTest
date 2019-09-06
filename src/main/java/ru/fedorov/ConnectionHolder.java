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
import java.util.List;

public class ConnectionHolder {

    private final String SITE = "https://easy.test-assignment-a.loyaltyplant.net";
    private final String KEY = "72b56103e43843412a992a8d64bf96e9";
    private final String REQUEST_GENRES = SITE + "/3/genre/movie/list?api_key=" + KEY;
    private final String REQUEST_AVERAGE_VOTE = SITE + "/3/discover/movie?api_key=" + KEY + "&page=";

    public ConnectionHolder() {}

    public List<Genre> getListGenre() throws IOException {
        URL urlGenre = new URL(REQUEST_GENRES);
        HttpURLConnection connection = (HttpURLConnection) urlGenre.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        Genres genres = mapper.readValue(urlGenre, new TypeReference<Genres>() {});

        return new ArrayList<>(genres.getGenres());
    }

    public int getMaxPage() throws IOException {
        int pages = 0;
        String link = REQUEST_AVERAGE_VOTE + "1";
        URL urlMaxPage = new URL(link);

        HttpURLConnection connection = (HttpURLConnection) urlMaxPage.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.readValue(urlMaxPage, ObjectNode.class);

        if (node.has("total_pages")) {
            pages = node.get("total_pages").asInt();
        }

        return pages;
    }

    public List<AverageVote> getAverageVoteByPage(int pageNum) throws IOException {
        URL urlAverageVotes = new URL(REQUEST_AVERAGE_VOTE + pageNum);

        HttpURLConnection connection = (HttpURLConnection) urlAverageVotes.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        Page page = mapper.readValue(urlAverageVotes, new TypeReference<Page>() {});

        return new ArrayList<>(page.getResults());
    }

}
