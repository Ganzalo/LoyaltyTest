package ru.fedorov.model;

import java.util.Map;

public interface AverageVoteCalculator {

    Map<Integer, String> getGenres();
    void start(int id);
    float getProgress();
    boolean isStop();
    void stop();
    float getResult() throws InterruptedException;

}
