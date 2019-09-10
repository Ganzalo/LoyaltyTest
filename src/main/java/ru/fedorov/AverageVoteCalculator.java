package ru.fedorov;

import ru.fedorov.model.VoteAverage;
import java.util.List;

public class AverageVoteCalculator {

    private ConnectionHolder connectionHolder;
    private int genreId;
    private float voteAverege = 0;
    private boolean voteInit = false;

    public AverageVoteCalculator(int genreId) {
        this.genreId = genreId;
    }

    public void getAverageVote() {
        connectionHolder = new ConnectionHolder();
        calculationAverageVote();
    }

    private void calculationAverageVote() {
        while (connectionHolder.hasNext()) {
            float vote = getAverageVote(connectionHolder.getAverageVoteNextPages(100));
            if ((int) vote != 0) {
                if (!this.voteInit) {
                    this.voteAverege = vote;
                    this.voteInit = true;
                    continue;
                }
                voteAverege = (vote + this.voteAverege) / 2;
                System.out.println("Обработка запроса! Текущие значение средний оценки за жанр = " + this.voteAverege);
            }
        }
        System.out.println("Обработка завершена! Значение средний оценки за жанр = " + this.voteAverege);
    }

    private float getAverageVote(List<VoteAverage> averageVotes) {
        float sum = 0;
        int count = 0;
        for(VoteAverage averageVote : averageVotes) {
            for (int genreIds : averageVote.getGenreIds()) {
                if (averageVote.getVoteCount() != 0 && genreIds == this.genreId) {
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
