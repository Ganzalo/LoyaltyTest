package ru.fedorov.workers;

public class CalculatorThread extends Thread {

    private AverageVoteCalculator voteAverageCalculator;

    public CalculatorThread(AverageVoteCalculator voteAverageCalculator) {
        this.voteAverageCalculator = voteAverageCalculator;
    }

    public boolean isStopCalculate() {
        return voteAverageCalculator.isStopCalculate();
    }

    public float getCurrProgress() {
        return voteAverageCalculator.getCurrProgress();
    }

    public float getResult() {
        return voteAverageCalculator.getAverageVote();
    }

    @Override
    public void interrupt() {
        voteAverageCalculator.setStopCalculate(true);
        super.interrupt();
    }

    @Override
    public void run() {
        voteAverageCalculator.startCalculate();
    }

}
