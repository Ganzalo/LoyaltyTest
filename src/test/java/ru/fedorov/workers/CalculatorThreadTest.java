package ru.fedorov.workers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorThreadTest {

    private CalculatorThread calculatorThread;

    @DisplayName("voteAverage Test")
    @Test
    void voteAverageTest() {
        calculatorThread = new CalculatorThread(new AverageVoteCalculator(-1));
        calculatorThread.start();
        Assertions.assertEquals(0, calculatorThread.getResult());
    }
}
