package ru.fedorov.model;

import ru.fedorov.model.dataholder.GenresHolder;

import java.util.Map;

public class LPCalculatorImpl implements AverageVoteCalculator {

    private CalculatorThread calculatorThread;

    @Override
    public Map<Integer, String> getGenres() {
        return GenresHolder.getGenres();
    }

    @Override
    public void start(int id) {
        calculatorThread = new CalculatorThread(new Calculator(id));
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
