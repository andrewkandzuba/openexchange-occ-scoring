package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFileScoreFactory {

    @Test
    void createFileScore() {
        var scoreFactory = new FileScoreFactory();
        var score = scoreFactory.load(TestFileScore.class.getResource("/testdata/names.txt"));

        Assertions.assertNotNull(score);
        Assertions.assertTrue(score instanceof FileScore);
    }
}
