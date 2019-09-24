package ru.fedorov.workers;

import ru.fedorov.connection.GenresHandler;
import ru.fedorov.model.Genre;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class GenresHolder {

    public static final Map<Integer, String> GENRES = genres();

    private static Map<Integer, String> genres() {
        return Collections.unmodifiableMap(new GenresHandler().getGenres().stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getName)));
    }

}
