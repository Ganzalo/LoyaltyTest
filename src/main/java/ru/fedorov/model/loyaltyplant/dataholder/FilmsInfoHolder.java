package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.util.Console;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_AVERAGE_VOTE;

/**
 * Получение информации о фильмах
 */

class FilmsInfoHolder {
    /**
     * Получение значения максимальной страницы
     */
    static int requestMaxPage() {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + "1"), ObjectNode.class);
        } catch (IOException e) {
            Console.writeMessage("Ошибка получения значения максимальной страницы");
        }

        if (node != null && node.has("total_pages"))
            return node.get("total_pages").asInt();

        return 0;
    }

    /**
     * Получает лист объектов FilmInfo за страницу по endpoint c помощью currentPage
     */
    static List<FilmInfo> requestPage(int currentPage) {
        Page page = null;
        try {
            page = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + currentPage),
                    new TypeReference<Page>() {});
        } catch (IOException e) {
            Console.writeMessage("Ошибка получения страницы ");
        }

        if (page == null) {
            Console.writeMessage("Пропуск страницы " + currentPage);
            return Collections.emptyList();
        }

        return new ArrayList<>(page.getResults());
    }
}
