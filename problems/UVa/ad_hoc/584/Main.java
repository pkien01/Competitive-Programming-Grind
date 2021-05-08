import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    int rollVal(char roll) {
        if (Character.isDigit(roll)) return (int)roll - '0';
        if (roll == 'X') return 10;
        else return -1;
    }
    int solve(String str) {
        String[] rolls = str.split(" ");
        int cntFrameIdx = 0;
        int[] scores = new int[10];
        for (int i = 0; i < rolls.length; i += 2) {
            if (cntFrameIdx >= 10) break;
            char curRoll = rolls[i].charAt(0);
            if (Character.isDigit(curRoll)) {
                if (i + 1 < rolls.length) {
                    char nextRoll = rolls[i + 1].charAt(0);
                    if (Character.isDigit(nextRoll))
                        scores[cntFrameIdx] = rollVal(curRoll) + rollVal(nextRoll);
                    else if (nextRoll == '/') 
                        scores[cntFrameIdx] = 10 + rollVal(rolls[i + 2].charAt(0));
                    else assert false;
                }
            }
            else if (curRoll == 'X') {
                if (rollVal(rolls[i + 2].charAt(0)) != -1) 
                    scores[cntFrameIdx] = 10 + rollVal(rolls[i + 1].charAt(0)) + rollVal(rolls[i + 2].charAt(0));
                else if (rolls[i + 2].charAt(0) == '/') scores[cntFrameIdx] = 10 + 10;
                i--;
            }
            else assert false;
            cntFrameIdx++;
        }
        int res = 0;
        for (int i = 0; i < 10; i++) res += scores[i];
        //System.out.println(Arrays.toString(scores));
        return res;
    }
    public static void main(String args[]) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextLine()) {
            String str = inp.nextLine();
            if (str.equals("Game Over")) break;
            System.out.println(obj.solve(str));
        }
    }
}