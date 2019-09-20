package ru.fedorov.workers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class VoteAverageCalculatorTests {

    private AverageVoteCalculator voteAverageCalculator;

    @DisplayName("voteAverage Test")
    @Test
    void voteAverageTest() {
        voteAverageCalculator = new AverageVoteCalculator(-1);
        Assertions.assertEquals(0, (int) voteAverageCalculator.voteAverage());
    }
}
