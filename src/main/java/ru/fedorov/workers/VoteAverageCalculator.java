package ru.fedorov.workers;

import ru.fedorov.connection.PagesHandler;
import ru.fedorov.model.VoteAverage;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static ru.fedorov.workers.GenresHolder.GENRES;

public class VoteAverageCalculator {

    private final static int PAGE_STEP = 20;

    private int genreId;

    public VoteAverageCalculator(int genreId) {
        this.genreId = genreId;
    }

    public float voteAverage() {
        PagesHandler pagesHolder = new PagesHandler();
        List<VoteAverage> voteAverages = new ArrayList<>();

        while (pagesHolder.hasNext()) {
            voteAverages.addAll(pagesHolder.getAverageVoteNextPages(PAGE_STEP));
            System.out.println("Получение данных! Обработано страниц " + pagesHolder.getCurrentPage() +
                    " из " + pagesHolder.getMaxPage());
        }
        System.out.println("Подсчет средней оценки... ");
        float voteAverage = formatFloat(calculateVoteAverage(voteAverages));
        System.out.println("Обработка завершена! Значение средней оценки за жанр " + GENRES.get(genreId) +
                " = " + voteAverage);
        return voteAverage;
    }

    private float calculateVoteAverage(List<VoteAverage> voteAverages) {
        float sum = 0;
        int count = 0;
        for(VoteAverage voteAverage : voteAverages) {
            for (int genreIds : voteAverage.getGenreIds()) {
                if (voteAverage.getVoteCount() != 0 && genreIds == this.genreId) {
                    sum += voteAverage.getVoteAverage();
                    count++;
                    break;
                }
            }
        }

        if(count == 0)
            return 0;

        return sum / count;
    }

    private float formatFloat(float voteAverage) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setRoundingMode(RoundingMode.DOWN);
        return new Float(formatter.format(voteAverage));
    }
}
