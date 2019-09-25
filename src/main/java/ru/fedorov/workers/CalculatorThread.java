package ru.fedorov.workers;

import java.awt.*;
import java.awt.event.KeyEvent;

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

        while (interrupted()) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            //maybe add smth
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

}
