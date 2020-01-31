package ru.fedorov.model.loyaltyplant.dataholder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.Page;

import java.io.IOException;
import java.net.URL;
import java.util.*;


import static ru.fedorov.model.loyaltyplant.dataholder.Constants.REQUEST_AVERAGE_VOTE;

public class FilmsInfoHolder {

    private int currentPage = 1;
    private int maxPage = requestMaxPage();

    private int requestMaxPage() {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + "1"), ObjectNode.class);
        } catch (IOException e) {
            //Console.writeMessage("Ошибка получения значения максимальной страницы");todo добавить лог
        }

        if (node != null && node.has("total_pages"))
            return 20;//TODO
        //return node.get("total_pages").asInt();

        return 0;
    }

    private List<FilmInfo> requestPage(int currentPage) {
        Page page = null;
        try {
            page = new ObjectMapper().readValue(new URL(REQUEST_AVERAGE_VOTE + currentPage),
                    new TypeReference<Page>() {});
        } catch (IOException e) {
            // Console.writeMessage("Ошибка получения страницы ");todo добавить лог
        }

        if (page == null) {
            //Console.writeMessage("Пропуск страницы " + currentPage);todo добавить лог
            return Collections.emptyList();
        }

        return new ArrayList<>(page.getResults());
    }

    public List<FilmInfo> getFilmsInfo() {
        if (!hasNextPage())
            return Collections.emptyList();

        List<FilmInfo> filmsInfo = new LinkedList<>();
        while (this.currentPage < maxPage) {
            filmsInfo.addAll(requestPage(this.currentPage));
            this.currentPage++;
        }

        return filmsInfo;
    }

    private boolean hasNextPage() {
        return this.maxPage > this.currentPage;
    }
}
