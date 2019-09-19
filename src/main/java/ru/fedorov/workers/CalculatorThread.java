package ru.fedorov.workers;

public class CalculatorThread extends Thread {

    private VoteAverageCalculator voteAverageCalculator;

    public CalculatorThread(VoteAverageCalculator voteAverageCalculator) {
        this.voteAverageCalculator = voteAverageCalculator;
    }

    @Override
    public void run() {
        voteAverageCalculator.voteAverage();
    }
}
