package ru.fedorov.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fedorov.model.Genre;
import ru.fedorov.model.Genres;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.connection.Consts.REQUEST_GENRES;

public class GenresHandler {

    public List<Genre> getGenres()  {
        Genres genres = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            genres = mapper.readValue(new URL(REQUEST_GENRES), new TypeReference<Genres>() {});
        } catch (IOException e) {
            System.out.println("Ошибка получения списка жанров.");
            e.printStackTrace();
        }

        if (genres == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(genres.getGenres());
    }
}
