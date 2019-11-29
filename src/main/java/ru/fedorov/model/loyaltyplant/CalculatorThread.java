package ru.fedorov.model.loyaltyplant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Бизнес логика отдельном потоке для
 * работы в фоновом режиме
 */

class CalculatorThread extends Thread {

    private Calculator calculator;

    CalculatorThread() {
        this.calculator = new Calculator();
    }

    Map<Integer, String> getGenres() {
        return calculator.getGenres();
    }

    void setGenreId(int id) {
        calculator.setGenreId(id);
    }

    boolean isStopCalculate() {
        return calculator.isStopCalculate();
    }

    float getCurrProgress() {
        return calculator.getProgress();
    }

    float getResult() {
        return calculator.getAverageVote();
    }

    @Override
    public void interrupt() {
        calculator.setStopCalculate(true);
        super.interrupt();
    }

    @Override
    public void run() {
        calculator.calculate();

        while (interrupted()) {
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!interrupted()) {

            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {

            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

}
