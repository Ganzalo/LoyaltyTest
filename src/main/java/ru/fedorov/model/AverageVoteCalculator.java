package ru.fedorov.model;

import java.util.Map;

/**
 * Интерфейс для взаимодействия UI с бизнес лгогикой
 */
public interface AverageVoteCalculator {

    Map<Integer, String> getGenres();

    void calculate(int id);

    float getProgress();

    boolean isStop();

    void stop();

    float getResult() throws InterruptedException;

}
