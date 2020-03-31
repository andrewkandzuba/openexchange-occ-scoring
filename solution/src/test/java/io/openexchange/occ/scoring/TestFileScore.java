package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFileScore {

    @Test
    void scoreFile() {
        var scoreFactory = new FileScoreFactory();
        var score = scoreFactory.load(TestFileScore.class.getResource("/testdata/names.txt"));
        Assertions.assertEquals(3194, score.total());
    }

    @Test
    void scoreBigData() {
        var scoreFactory = new FileScoreFactory();
        var score = scoreFactory.load(TestFileScore.class.getResource("/testdata/bigdata.txt"));
        Assertions.assertEquals(871198282, score.total());
    }
}
