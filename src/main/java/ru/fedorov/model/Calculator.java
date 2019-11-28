package ru.fedorov.model;

import ru.fedorov.model.dataholder.PagesHolder;
import ru.fedorov.model.vo.pages.Page;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Calculator {

    private final static int PAGE_STEP = 30;
    private PagesHolder pagesHandler = new PagesHolder();
    private float currProgress;
    private float averageVoteResult;
    private boolean stopCalculate = false;

    private int genreId;

    public Calculator(int genreId) {
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
        List<Page> averageVoteByFilms = new ArrayList<>();

        while (pagesHandler.hasNext() && !stopCalculate) {
            averageVoteByFilms.addAll(pagesHandler.getAverageVotesNextPages(PAGE_STEP));
            setPercentageProcessData();
        }

        averageVoteResult = formatFloat(calculateAverageVoteByGenre(averageVoteByFilms));
        stopCalculate = true;
    }

    private float calculateAverageVoteByGenre(List<Page> averageVotesByFilms) {
        float sum = 0;
        int count = 0;
        for(Page averageVoteByFilm : averageVotesByFilms) {
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
