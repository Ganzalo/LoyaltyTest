package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.Page;

import java.io.IOException;
import java.net.URL;
import java.util.*;


import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_AVERAGE_VOTE;

public class FilmsInfoHolder {

    private Logger logger = LoggerFactory.getLogger("businessLogic");
    private int currentPage = 1;
    private int maxPage = requestMaxPage();

    private int requestMaxPage() {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + "1"), ObjectNode.class);
        } catch (IOException e) {
            logger.warn("Ошибка получения кол-во страниц");
        }

        if (node != null && node.has("total_pages")) {
            logger.info("Кол-во страниц = " + node.get("total_pages").asInt());
            return node.get("total_pages").asInt();//TODO set 20 for test
        }

        logger.warn("Кол-во страниц = 0");
        return 0;
    }

    private List<FilmInfo> requestPage() {
        Page page = null;
        try {
            page = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + this.currentPage),
                    new TypeReference<Page>() {});
        } catch (IOException e) {
            logger.warn("Ошибка получения страницы " + this.currentPage);
        }

        if (page == null) {
            logger.warn("Пропуск страницы " + this.currentPage);
            return Collections.emptyList();
        }

        logger.info("Получения страницы " + this.currentPage);
        return new ArrayList<>(page.getResults());
    }

    public List<FilmInfo> getFilmsInfo() {
        if (!hasNextPage())
            return Collections.emptyList();

        List<FilmInfo> filmsInfo = new LinkedList<>();
        while (this.currentPage < maxPage) {
            filmsInfo.addAll(requestPage());
            this.currentPage++;
        }

        logger.info("Получены все страницы");
        return filmsInfo;
    }

    private boolean hasNextPage() {
        return this.maxPage > this.currentPage;
    }
}
