package com.denre.game;

import java.util.Random;

public class GuessingGame {

    private final int randomNum = new Random().nextInt(10) + 1;
    private int count = 0;

    public String guess(int guessCode) {
        count++;

        String tryTxt = count == 1 ? "try" : "tries";
        String winMSG = String.format("you got it in %d %s!", count, tryTxt);
        String response = null;

        if (count == 4 && guessCode != getRandomNumber()) {
            response = String.format("You didn't get it! and you had %d %s. Game Over.", count, tryTxt);
        } else if (count > 4) {
            response = String.format("Sorry, You are limited to only 4 tries.Your game is Over");
        } else {
            String guessMSG = null;
            if (guessCode < getRandomNumber()) {
                guessMSG = "Think Something High";
            } else if (guessCode > getRandomNumber()) {
                guessMSG = "Think Something Low";
            } else {
                guessMSG = "";
            }

            String msg = String.format("You didn't get it! %s", guessMSG);
            response = guessCode == getRandomNumber() ? winMSG : msg;
        }
        return response;
    }

    public int getRandomNumber() {
        return randomNum;
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        boolean loopBreak = true;
        do {
            String userInput = System.console().readLine("Enter a Number : ");
            if ("q".equals(userInput)) {
                return;
            }
            String output = game.guess(Integer.parseInt(userInput));
            System.out.println(output);
            if (output.contains("you got it") || output.contains("Over")) {
                loopBreak = false;
            }

        } while (loopBreak);


    }
}
