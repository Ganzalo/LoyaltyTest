package ru.fedorov;

import ru.fedorov.model.AverageVote;

import java.io.IOException;
import java.util.List;

public class AverageVoteCalculator {

    private ConnectionHolder connectionHolder;
    private int genreId;
    private float currAvergeVote = 0;
    private float funalAvergeVote = 0;

    public AverageVoteCalculator(int genreId) {
        this.genreId = genreId;
    }

    public void getAverageVote() {
        connectionHolder = new ConnectionHolder();
        calculationAverageVote();
    }

    private void calculationAverageVote() {

            int count = 0;
            float averageVoteTemp = 0;
            while ((currPage % 10) == 0 || currPage < maxPage) {
                float average = 0;
                try {
                    average += getAverageVote(connectionHolder.getAverageVoteByPage(currPage));
                } catch (IOException e) {
                    System.out.println("Ошибка получения данных за страницу!");
                    e.printStackTrace();
                }
                currPage++;
                System.out.println("average = " + average + " count = " + count);
                if((int)average != 0) {
                    averageVoteTemp += average;
                    count++;
                }

            if((int) averageVoteTemp != 0) {
                setAvergeVote(averageVoteTemp / count);
                System.out.println( "Обработка запроса! Текущие значение средний оценки за жанр = " + this.avergeVote);
            }
        }
    }

    private void setAvergeVote(float avergeVote) {
        if ((int) this.avergeVote == 0)
            this.avergeVote = avergeVote;
        else
            this.avergeVote =  (this.avergeVote + avergeVote) / 2;
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
