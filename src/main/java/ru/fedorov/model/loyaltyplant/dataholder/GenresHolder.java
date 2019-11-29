package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fedorov.model.loyaltyplant.vo.genres.Genre;
import ru.fedorov.model.loyaltyplant.vo.genres.Genres;
import ru.fedorov.util.Console;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    static List<Genre> requestGenres() {
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

}
