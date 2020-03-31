package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestScore {

    private Score score = () -> 100;

    @Test
    void regularScoreOperations() {
        Assertions.assertEquals(100, score.total());
    }
}
