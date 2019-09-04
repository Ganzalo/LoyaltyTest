package ru.fedorov;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fedorov.model.Genre;
import ru.fedorov.model.Genres;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHolder {

    private String key = "72b56103e43843412a992a8d64bf96e9";
    private String urlSite;

    public ConnectionHolder(String urlSite) {
       this.urlSite = urlSite;
    }

    public List<Genre> getListGenre() throws IOException {
        String link = urlSite + "3/genre/movie/list?api_key=$" + key;
        URL urlGenre = new URL(link);

        HttpURLConnection connection = (HttpURLConnection) urlGenre.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        //GenreModel genreModel = mapper.readValue(urlGenre, GenreModel.class);
        //Genre[] myObjects = mapper.readValue(urlGenre, Genre[].class);
        Genres genres = mapper.readValue(urlGenre, new TypeReference<Genres>() {});
        System.out.println(genres);


        //System.out.println(genreModel);
       //System.out.println(myObjects);
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        System.out.println(response.toString());
        return new ArrayList<Genre>();
    }
}
