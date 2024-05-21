package se.gmail.game.util;

import java.util.Comparator;

public class SpriteComparator implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        // Extract the numeric part of the filenames
        int num1 = extractNumber(o1);
        int num2 = extractNumber(o2);

        // Compare the numeric parts
        if (num1 == num2) {
            // If numeric parts are equal, compare the filenames lexicographically
            return o1.compareTo(o2);
        }
        return num1 - num2;
    }

    private int extractNumber(String s) {
        // Remove all non-numeric characters
        String num = s.replaceAll("\\D", "");
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }
}
