package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Components.Score;
import Panels.Game.Generator;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Generator.
 * Tests the methods difficultyY and difficultyX which
 * determine the position of generated objects based on the player's score.
 */
class GeneratorTest {
    private Generator generator;
    private Score score;

    /**
     * Setup before each test.
     * Creates new Score and Generator instances.
     */
    @BeforeEach
    void setUp() {
        this.score = new Score();
        this.generator = new Generator(score,null);
    }

    /**
     * Test of difficultyY method.
     * Verifies that the method correctly decreases the Y value based on the player's score.
     */
    @Test
    void difficultyYTest() {
        int lastY = 1000;

        score.setPlayerScore(10);
        Assertions.assertEquals(850, generator.difficultyY(lastY));
        score.setPlayerScore(25);
        Assertions.assertEquals(800, generator.difficultyY(lastY));
        score.setPlayerScore(35);
        Assertions.assertEquals(750, generator.difficultyY(lastY));
        score.setPlayerScore(45);
        Assertions.assertEquals(725, generator.difficultyY(lastY));
        score.setPlayerScore(60);
        Assertions.assertEquals(700, generator.difficultyY(lastY));
    }

    /**
     * Test of difficultyX method.
     * Verifies that difficultyX returns a value within the expected range based on the player's score.
     */
    @Test
    void difficultyXTest() {
        int lastX = 500;

        score.setPlayerScore(10);
        int val = generator.difficultyX(lastX);
        assertTrue(val >= lastX - 400 && val <= lastX + 400);
        score.setPlayerScore(25);
        val = generator.difficultyX(lastX);
        assertTrue(val >= lastX - 420 && val <= lastX + 420);
        score.setPlayerScore(35);
        val = generator.difficultyX(lastX);
        assertTrue(val >= lastX - 440 && val <= lastX + 440);
        score.setPlayerScore(45);
        val = generator.difficultyX(lastX);
        assertTrue(val >= lastX - 460 && val <= lastX + 460);
        score.setPlayerScore(60);
        val = generator.difficultyX(lastX);
        assertTrue(val >= lastX - 480 && val <= lastX + 480);
    }
}