import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(50,score.getBestScore());
    }

    @Test
    void setBestScoreOnMenuFalse() {
        score.setBestScore(100);
        score.setPlayerScore(50);
        boolean result = score.setBestScoreOnMenu();

        assertFalse(result);
        assertEquals(100,score.getBestScore());
    }
}