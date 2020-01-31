package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fedorov.model.loyaltyplant.vo.genres.GenreInfo;
import ru.fedorov.model.loyaltyplant.vo.genres.Genres;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_GENRES;

class GenresHolder {

    public List<GenreInfo> requestGenres() {
        Genres genres = null;
        try {
            genres = new ObjectMapper().readValue(new URL(REQUEST_GENRES), new TypeReference<Genres>() {});
        } catch (IOException e) {
            //Console.writeMessage("Ошибка получения списка жанров.");todo добавить лог
        }

        return genres == null ? new ArrayList<>(Collections.emptyList()) : new ArrayList<>(genres.getGenres());
    }

}
