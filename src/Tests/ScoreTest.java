package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Components.Score;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    private Score score;

    @BeforeEach
    void setUp() {
        this.score = new Score();
    }

    @Test
    void setBestScoreOnMenuTrue() {
        score.setPlayerScore(50);
        boolean result = score.setBestScoreOnMenu();
        assertTrue(result);
        Assertions.assertEquals(50,score.getBestScore());
    }

    @Test
    void setBestScoreOnMenuFalse() {
        score.setBestScore(100);
        score.setPlayerScore(50);
        boolean result = score.setBestScoreOnMenu();

        assertFalse(result);
        Assertions.assertEquals(100,score.getBestScore());
    }
}