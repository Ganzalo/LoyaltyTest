package ru.fedorov.model.loyaltyplant;

import ru.fedorov.model.loyaltyplant.dataholder.DataHolder;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Бизнес логика приложения считает среднию оценку за жанр
 * данные берет из dataHolder.
 */

class Calculator {

    /**
     * Шаг получение страниц для подсчета
     */
    private final static int PAGE_STEP = 30;
    /**
     * Данные для бизнес-логики
     */
    private DataHolder dataHolder;
    /**
     * Текущий прогресс страниц
     */
    private float progress;
    /**
     * Результат подсчета
     */
    private float averageVote;
    /**
     * Флаг прекращения подсчета
     */
    private boolean stopCalculate = false;
    /**
     * Жанр за который идет подсчет
     */
    private int genreId;

    void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    Map<Integer, String> getGenres() {
        return this.dataHolder.getGenres();
    }

    boolean isStopCalculate() {
        return this.stopCalculate;
    }

    void setStopCalculate(boolean stop) {
        this.stopCalculate = stop;
    }

    float getProgress() {
        return this.progress;
    }

    float getAverageVote() {
        return this.averageVote;
    }

    Calculator() {
        dataHolder = new DataHolder();
    }
    /**
     * Подсчет средней оценки за жанр
     */
    void calculate() {
        averageVote = calculateAverageVote(collectData());
        stopCalculate = true;
    }

    /**
     * Сбор данных для подсчета
     *
     * @return List<FilmInfo>
     */
    private List<FilmInfo> collectData() {
        List<FilmInfo> filmsInfo = new ArrayList<>();

        while (dataHolder.hasNextPage() && !stopCalculate) {
            filmsInfo.addAll(dataHolder.getFilmsInfo(PAGE_STEP));
            setProgressData();
        }
        return filmsInfo;
    }

    /**
     * Логика подсчета средней оценки за жанр
     *
     * @param filmsInfo информация о фильмах
     * @return float средняя оценка за жанр
     */
    private float calculateAverageVote(List<FilmInfo> filmsInfo) {
        float sum = 0;
        int count = 0;
        for (FilmInfo averageVoteByFilm : filmsInfo) {
            for (int genreIds : averageVoteByFilm.getGenreIds()) {
                if (averageVoteByFilm.getVoteCount() != 0 && genreIds == this.genreId) {
                    sum += averageVoteByFilm.getAverageVote();
                    count++;
                    break;
                }
            }
        }

        if (count == 0)
            return 0;
        return formatFloat(sum / count);
    }

    /**
     * Установка текущего прогресса полученных данных
     */
    private void setProgressData() {
        int currPage = dataHolder.getCurrentPage();
        int maxPage = dataHolder.getMaxPage();
        progress = formatFloat((float) currPage / maxPage * 100);
    }

    private float formatFloat(float number) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setRoundingMode(RoundingMode.DOWN);
        return new Float(formatter.format(number));
    }
}
