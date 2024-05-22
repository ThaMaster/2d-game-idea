package se.gmail.game.util;

import java.util.Random;

public class Util {
    public static double randomDouble(double minVal, double maxVal) {
        Random rand = new Random();
        return minVal + (maxVal - minVal) * rand.nextDouble();
    }

    public static int randomInt(int minVal, int maxVal) {
        Random rand = new Random();
        return rand.nextInt((maxVal - minVal) + 1) + minVal;
    }

    public static boolean probabilityCheck(double probability) {
        Random rand = new Random();
        return rand.nextDouble() < probability;
    }
}
