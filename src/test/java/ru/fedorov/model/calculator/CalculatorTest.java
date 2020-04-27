package ru.fedorov.model.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fedorov.entity.Film;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @DisplayName("Films null")
    @Test
    void testNullArg() {
        Assertions.assertEquals(0.0 , calculator.calculateAverageVote(null));
    }

    @DisplayName("Films empty")
    @Test
    void testEmptyArg() {
        Assertions.assertEquals(0.0 , calculator.calculateAverageVote(new ArrayList<>()));
    }

    @DisplayName("Films arg with 0 count")
    @Test
    void testZeroCountArg() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(1, new int [1], 0, 7.0f));
        Assertions.assertEquals(0.0 , calculator.calculateAverageVote(films));
    }

    @DisplayName("Films args")
    @Test
    void testArgs() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(1, new int [1], 1, 5.0f));
        films.add(new Film(1, new int [1], 1, 7.0f));
        films.add(new Film(1, new int [1], 1, 4.0f));
        films.add(new Film(1, new int [1], 1, 8.0f));
        Assertions.assertEquals(6.0 , calculator.calculateAverageVote(films));
    }

    @DisplayName("Films args average vote = 0")
    @Test
    void testArgWithZero() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(1, new int [1], 1, 0.0f));
        Assertions.assertEquals(0.0 , calculator.calculateAverageVote(films));
    }
}
