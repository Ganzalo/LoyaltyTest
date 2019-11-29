package ru.fedorov.model.loyaltyplant;

import ru.fedorov.model.AverageVoteCalculator;

import java.util.Map;

/**
 * Реализация AverageVoteCalculator для сайта loyaltyPlant
 */
public class LPCalculatorImpl implements AverageVoteCalculator {

    private CalculatorThread calculatorThread;

    public LPCalculatorImpl() {
        calculatorThread = new CalculatorThread();
    }

    @Override
    public Map<Integer, String> getGenres() {
        return calculatorThread.getGenres();
    }

    @Override
    public void calculate(int id) {
        calculatorThread.setGenreId(id);
        calculatorThread.start();
    }

    @Override
    public float getProgress() {
        return calculatorThread.getCurrProgress();
    }

    @Override
    public boolean isStop() {
        return !(calculatorThread.isInterrupted() || calculatorThread.isStopCalculate());
    }

    @Override
    public void stop() {
        calculatorThread.interrupt();
    }

    @Override
    public float getResult() throws InterruptedException {
        calculatorThread.join();
        return calculatorThread.getResult();
    }

}
