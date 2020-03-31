package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URI;

public class TestScoreFactory {

    @Mock
    private Score scoreA;
    @Mock
    private Score scoreB;

    private ScoreFactory scoreFactoryA = new ScoreFactory() {
        @Override
        public Score load(URI resource) {
            return scoreA;
        }
    };

    private ScoreFactory scoreFactoryB = new ScoreFactory() {
        @Override
        public Score load(URI resource) {
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
    void createScoreSuccess() {
        URI uri = URI.create("/tmp/file");

        var score = scoreFactoryA.load(uri);
        Assertions.assertEquals(100, score.total());

        score = scoreFactoryB.load(uri);
        Assertions.assertEquals(200, score.total());
    }
}
