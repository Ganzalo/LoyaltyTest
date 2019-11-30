package ru.fedorov.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.model.loyaltyplant.LPCalculatorImpl;

public class CalculatorThreadTest {

    private LPCalculatorImpl calculatorThread;

    @DisplayName("voteAverage Test")
    @Disabled("Тест заниает много времени")
    @Test
    void voteAverageTest() {
        calculatorThread = new LPCalculatorImpl();
        calculatorThread.calculate(-1);
        float result = -1.0f;
        try {
            result = calculatorThread.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(0, result);
    }
}
