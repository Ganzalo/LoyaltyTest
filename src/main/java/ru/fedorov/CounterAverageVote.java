package ru.fedorov;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.IOException;

public class CounterAverageVote {

    ConnectionHolder connectionHolder;
    int genreId;
    int maxPage;
    int currPage = 1;


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
            try {
                System.out.println(connectionHolder.getAverageVoteByPage(currPage));
            } catch (IOException e) {
                System.out.println("Ошибка получения данных за страницу!");
                e.printStackTrace();
            }
            break;
            //currPage++;
        }
    }
}
