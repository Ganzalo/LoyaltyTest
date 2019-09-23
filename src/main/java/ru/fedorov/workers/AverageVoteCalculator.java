package ru.fedorov.workers;

import ru.fedorov.connection.PagesHandler;
import ru.fedorov.model.AverageVote;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static ru.fedorov.workers.GenresHolder.GENRES;

public class AverageVoteCalculator {

    private final static int PAGE_STEP = 30;
    private PagesHandler pagesHandler;
    private float currProgress;
    private float averageVoteResult;
    private boolean stopCalculate = false;

    private int genreId;

    public AverageVoteCalculator(int genreId) {
        this.genreId = genreId;
    }

    public boolean isStopCalculate() {
        return stopCalculate;
    }

    public void setStopCalculate(boolean stop) {
        this.stopCalculate = stop;
    }

    public float getCurrProgress() {
        return currProgress;
    }

    public float getAverageVote() {
        return averageVoteResult;
    }

    public void startCalculate() {
        if(GENRES.get(genreId) == null) {
            stopCalculate = true;
            averageVoteResult = 0.0f;
            return;
        }

        pagesHandler = new PagesHandler();
        List<AverageVote> averageVoteByFilms = new ArrayList<>();

        while (pagesHandler.hasNext() && !stopCalculate) {
            averageVoteByFilms.addAll(pagesHandler.getAverageVotesNextPages(PAGE_STEP));
            setPercentageProcessData();
        }

        averageVoteResult = formatFloat(calculateAverageVoteByGenre(averageVoteByFilms));
        stopCalculate = true;
    }

    private float calculateAverageVoteByGenre(List<AverageVote> averageVotesByFilms) {
        float sum = 0;
        int count = 0;
        for(AverageVote averageVoteByFilm : averageVotesByFilms) {
            for (int genreIds : averageVoteByFilm.getGenreIds()) {
                if (averageVoteByFilm.getVoteCount() != 0 && genreIds == this.genreId) {
                    sum += averageVoteByFilm.getAverageVote();
                    count++;
                    break;
                }
            }
        }

        if(count == 0)
            return 0;
        return sum / count;
    }

    private void setPercentageProcessData() {
        int currPage = pagesHandler.getCurrentPage();
        int maxPage = pagesHandler.getMaxPage();
        float result = (float) currPage / maxPage * 100;
        currProgress = formatFloat(result);
    }

    private float formatFloat(float number) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setRoundingMode(RoundingMode.DOWN);
        return new Float(formatter.format(number));
    }
}
