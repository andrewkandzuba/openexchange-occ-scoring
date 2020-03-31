package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.net.URL;

public class TestScoreFactory {

    @Mock
    private Score scoreA;
    @Mock
    private Score scoreB;

    private ScoreFactory scoreFactoryA = new ScoreFactory() {
        @Override
        public Score load(URL resource) {
            return scoreA;
        }
    };

    private ScoreFactory scoreFactoryB = new ScoreFactory() {
        @Override
        public Score load(URL resource) {
            return scoreB;
        }
    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(scoreA.total()).thenReturn(100);
        Mockito.when(scoreB.total()).thenReturn(200);
    }

    @Test
    void createScoreSuccess() throws MalformedURLException {
        URL url = new URL("file://tmp/file");

        var score = scoreFactoryA.load(url);
        Assertions.assertEquals(100, score.total());

        score = scoreFactoryB.load(url);
        Assertions.assertEquals(200, score.total());
    }
}
