package ru.fedorov.workers;

public class CalculatorThread extends Thread {

    private AverageVoteCalculator voteAverageCalculator;

    public CalculatorThread(AverageVoteCalculator voteAverageCalculator) {
        this.voteAverageCalculator = voteAverageCalculator;
    }


    public float getCurrResult() {
        return voteAverageCalculator.getCurrProgress();
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        voteAverageCalculator.voteAverage();
    }
}
