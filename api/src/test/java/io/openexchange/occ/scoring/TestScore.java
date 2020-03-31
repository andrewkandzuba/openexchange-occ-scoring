package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestScore {

    private Score score = new Score(){
        @Override
        public int total() {
            return 100;
        }

        @Override
        public int name(String name) throws NameNotFoundException {
            if (name.equalsIgnoreCase("LINDA"))
                return 160;
            throw new NameNotFoundException(String.format("There is no score data for: %s", name));
        }
    };

    @Test
    void regularScoreOperations() throws NameNotFoundException {
        Assertions.assertEquals(100, score.total());
        Assertions.assertEquals(160, score.name("LINDA"));
    }

    @Test
    void scoreNotFoundForName() throws NameNotFoundException {
        Assertions.assertThrows(NameNotFoundException.class, () -> score.name("ALICE"));
    }
}
