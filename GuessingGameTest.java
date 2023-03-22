package com.denre.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessingGameTest {

    public static final int GAME_RANDOMNESS_RETRIES = 100;
    //    public static final GuessingGame game = new GuessingGame();
    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    public void testOneTryWin() {
        game = new GuessingGame();
        int randNum = game.getRandomNumber();
        String message = game.guess(randNum);
        assertEquals("you got it in 1 try!", message);
    }

    @Test
    public void testLoseNegNum() {
//        int randNum = game.getRandomNumber();
        String message = game.guess(-1);
        assertEquals("You didn't get it! Think Something High", message);
    }

    @Test
    public void testLosePosNum() {
        int randNum = game.getRandomNumber();
        String message = game.guess(randNum + 1);
        assertEquals("You didn't get it! Think Something Low", message);
    }

    @Test
//    @RepeatedTest(10)
    public void testRandomNumberGenerated() {
        int[] randomNumCount = new int[11];
        for (int count = 0; count < GAME_RANDOMNESS_RETRIES; count++) {
            GuessingGame game = new GuessingGame();
            int randNum = game.getRandomNumber();
            randomNumCount[randNum] = 1;
        }
        int sum = 0;
        for (int count = 0; count < 11; count++) {
            sum += randomNumCount[count];
        }
        assertEquals(10, sum);
    }

    @Test
//    @RepeatedTest(100)
//    @Disabled
    public void testFourWrongGuesses() {
        GuessingGame game = new GuessingGame();
        makeThreeWrongGuesses(game);
        String message = game.guess(-5);
        assertEquals("You didn't get it! and you had 4 tries. Game Over.", message);
    }

    @Test
    public void testThreeWrongOneTrue() {
        int correctAns = game.getRandomNumber();
        makeThreeWrongGuesses(game);
        String message = game.guess(correctAns);
        assertEquals("you got it in 4 tries!", message);
    }

    private void makeThreeWrongGuesses(GuessingGame game) {
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
    }

    @Test
    public void testTwoWrongOneTrue() {
        int correctAns = game.getRandomNumber();
        game.guess(-3);
        game.guess(-3);
        String message = game.guess(correctAns);
        assertEquals("you got it in 3 tries!", message);
    }

    @Test
    public void testSixWrongGuesses() {
        game.guess(-8);
        game.guess(-8);
        game.guess(-8);
        game.guess(-8);
        game.guess(-8);
        String message = game.guess(-8);
        assertEquals("Sorry, You are limited to only 4 tries.Your game is Over", message);
    }
}
