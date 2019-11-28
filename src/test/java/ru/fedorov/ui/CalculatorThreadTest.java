package ru.fedorov.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.Calculator;
import ru.fedorov.model.CalculatorThread;

public class CalculatorThreadTest {

    private CalculatorThread calculatorThread;

    @DisplayName("voteAverage Test")
    @Test
    void voteAverageTest() {
        calculatorThread = new CalculatorThread(new Calculator(-1));
        calculatorThread.start();
        Assertions.assertEquals(0, calculatorThread.getResult());
    }
}
