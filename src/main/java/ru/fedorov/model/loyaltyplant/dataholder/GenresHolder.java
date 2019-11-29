package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fedorov.Console;
import ru.fedorov.model.loyaltyplant.vo.genres.Genre;
import ru.fedorov.model.loyaltyplant.vo.genres.Genres;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_GENRES;

/**
 * Получение жанров по endpoint
 */

class GenresHolder {

    /**
     * Запрашивает список жанров по endpoint
     *
     * @return List<Genre> если еще есть страницы, иначе Collections.emptyList()
     */
    private static List<Genre> requestGenres() {
        Genres genres = null;
        try {
            genres = new ObjectMapper().readValue(new URL(REQUEST_GENRES), new TypeReference<Genres>() {
            });
        } catch (IOException e) {
            Console.writeMessage("Ошибка получения списка жанров.");
        }

        if (genres == null)
            return Collections.emptyList();
        else
            return new ArrayList<>(genres.getGenres());
    }

    /**
     * Перобразует List<Genre> в Map<Integer, String> и оборачивает в unmodifiableMap
     *
     * @return Map<Integer, String> обернутую в unmodifiableMap
     */
    static Map<Integer, String> getGenres() {
        return Collections.unmodifiableMap(requestGenres().stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getName)));
    }
}
