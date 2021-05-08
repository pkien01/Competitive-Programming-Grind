import java.util.Random;
import java.io.*;

class GenerateTestCases {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File outputFile = new File(IODir + "/input.txt");
        try {
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        GenerateTestCases obj = new GenerateTestCases();
        obj.runFromFile();
        int n = 1000;
        final char[] ranks = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
        final char[] suits = {'S', 'H', 'D', 'C'};
        while (n > 0) {
            Random rand1 = new Random(), rand2 = new Random();
            for (int i = 0; i < 13; i++) {
                char curRank = ranks[rand1.nextInt(ranks.length)];
                char curSuit = suits[rand2.nextInt(suits.length)];
                System.out.format("%c%c", curRank, curSuit);
                if (i < 13) System.out.print(' ');
            }
            System.out.println();
            n--;
        }
    }
}