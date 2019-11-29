package ru.fedorov.model.loyaltyplant.dataholder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FilmsInfoHolderTests {


    @DisplayName("Max page")
    @Test
    void getAverageVoteNextPagesChangesField() {
        Assertions.assertTrue(FilmsInfoHolder.maxPage() > 0);
    }

    @DisplayName("Return not null")
    @Test
    void getAverageVoteNextPagesNotNull() {
        if (FilmsInfoHolder.maxPage() > 1) {
            Assertions.assertNotNull(FilmsInfoHolder.getPage(1));
        }
    }

}
