package ru.fedorov.model.dataholder.loyaltyplant;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fedorov.model.dataholder.loyaltyplant.vo.genres.GenreInfo;
import ru.fedorov.model.dataholder.loyaltyplant.vo.genres.Genres;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.model.dataholder.loyaltyplant.Constants.REQUEST_GENRES;

class GenresHolder {

    private Logger logger = LoggerFactory.getLogger("businessLogic");

    public List<GenreInfo> requestGenres() {
        Genres genres = null;
        try {
            genres = new ObjectMapper().readValue(new URL(REQUEST_GENRES), new TypeReference<Genres>() {});
        } catch (IOException e) {
            logger.warn("Ошибка получения списка жанров");
        }

        logger.info("Получен список жанров");
        return genres == null ? new ArrayList<>(Collections.emptyList()) : new ArrayList<>(genres.getGenres());
    }

}
