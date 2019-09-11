package ru.fedorov.workers;

import ru.fedorov.connection.GenresHandler;
import ru.fedorov.model.Genre;

import java.util.Map;
import java.util.stream.Collectors;

public class GenresHolder {

    public static final Map<Integer, String> GENRES = genres();

    public static Map<Integer, String> genres() {
        GenresHandler genreHolder = new GenresHandler();

        return genreHolder.getGenres().stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getName));
    }

}
