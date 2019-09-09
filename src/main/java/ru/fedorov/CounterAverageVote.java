package ru.fedorov;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import ru.fedorov.model.AverageVote;

import java.io.IOException;
import java.util.List;

public class CounterAverageVote {

    ConnectionHolder connectionHolder;
    int genreId;
    int maxPage;
    int currPage = 1;
    float avergeVote = 0;


    public CounterAverageVote(int genreId) {
        this.genreId = genreId;
    }

    public void startCounting() { }

    public void getAverageVote() {
        connectionHolder = new ConnectionHolder();
        int pages = -1;
        try {
            pages = connectionHolder.getMaxPage();
            if(pages < 1)
                throw new IllegalArgumentException();
        } catch (IOException e) {
            System.out.println("Ошибка подсчета средний оценки за жанр");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Отсуствует информация по средний оценки за фильмы");
            e.printStackTrace();
        }
        maxPage = pages;
        calculationAverageVote();
    }

    private void getMaxPage() {
        connectionHolder = new ConnectionHolder();
        int pages = -1;
        try {
            pages = connectionHolder.getMaxPage();
            if(pages < 1)
                throw new IllegalArgumentException();
        } catch (IOException e) {
            System.out.println("Ошибка подсчета средний оценки за жанр");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Отсуствует информация по средний оценки за фильмы");
            e.printStackTrace();
        }
        maxPage = pages;
    }

    private void calculationAverageVote() {
        while(currPage < maxPage) {
                int count = 0;
                float averageVoteTemp = 0;
                while ((currPage % 10) == 0 && currPage < maxPage) {
                    try {
                        averageVoteTemp += getAverageVote(connectionHolder.getAverageVoteByPage(currPage));
                    } catch (IOException e) {
                        System.out.println("Ошибка получения данных за страницу!");
                        e.printStackTrace();
                    }
                    count++;
                }

            break;
            //currPage++;
        }
    }

    private float getAverageVote(List<AverageVote> averageVotes) {
        float sum = 0.0f;
        int count = 0;
        for(AverageVote averageVote : averageVotes) {
            for (int genreIds : averageVote.getGenreIds()) {
                if (genreIds == this.genreId) {
                    sum += averageVote.getVoteAverage();
                    count++;
                    break;
                }
            }
        }
        if(count == 0)
            return 0;
        return sum / count;
    }
}
