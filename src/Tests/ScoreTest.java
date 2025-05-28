package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Components.Score;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Score component.
 */
class ScoreTest {
    private Score score;

    /**
     * Initializes a new score before each test.
     */

    @BeforeEach
    void setUp() {
        this.score = new Score();
    }

    /**
     * Tests setting best score when current score is higher.
     */
    @Test
    void setBestScoreOnMenuTrue() {
        score.setPlayerScore(50);
        boolean result = score.setBestScoreOnMenu();
        assertTrue(result);
        Assertions.assertEquals(50,score.getBestScore());
    }

    /**
     * Tests keeping best score when current score is lower.
     */
    @Test
    void setBestScoreOnMenuFalse() {
        score.setBestScore(100);
        score.setPlayerScore(50);
        boolean result = score.setBestScoreOnMenu();

        assertFalse(result);
        Assertions.assertEquals(100,score.getBestScore());
    }
}