import java.util.*;
import java.io.*;
import java.lang.*;
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
    int suitVal(char c) {
        switch (c) {
            case 'S': return 0;
            case 'H': return 1;
            case 'D': return 2;
            case 'C': return 3;
            default: return -1;
        }
    }
    char suitChar(int idx) {
        switch (idx) {
            case 0: return 'S';
            case 1: return 'H';
            case 2: return 'D';
            case 3: return 'C';
            default: return (char)0;
        }
    }
    boolean allTrue(boolean[] arr) {
        for (boolean x: arr) 
            if (!x) return false;
        return true;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextLine()) {
            String line = inp.nextLine();

            String[] cards = line.split(" ");
            assert cards.length == 13;
            int[] cnt = new int[4];
            for (String card: cards) cnt[obj.suitVal(card.charAt(1))]++;
            int points = 0;
            boolean[] stopped = new boolean[4];
            int rule2 = 0, rule3 = 0, rule4 = 0;
            for (String card: cards) {
                char rank = card.charAt(0);
                int suit = obj.suitVal(card.charAt(1));
                switch (rank) {
                    case 'A': 
                        points += 4; 
                        stopped[suit] = true;
                        break;
                    case 'K': 
                        points += 3; 
                        if (cnt[suit] < 2) rule2++;
                        else stopped[suit] = true;
                        break;
                    case 'Q': 
                        points += 2; 
                        if (cnt[suit] < 3) rule3++;
                        else stopped[suit] = true;
                        break;
                    case 'J': 
                        points += 1; 
                        if (cnt[suit] < 4) rule4++; 
                        break;
                }
            }
            //System.out.println(rule2 + " " + rule3 + " " + rule4);
            points -= (rule2 + rule3 + rule4);
            int maxCardsSuit = -1, rules567 = 0;
            for (int i = 0; i < 4; i++) {
                switch (cnt[i]) {
                    case 0: rules567 += 2; 
                    //System.out.println(obj.suitChar(i));
                        break;
                    case 1: rules567 += 2; 
                    //System.out.println(obj.suitChar(i));
                    break;
                    case 2: rules567 += 1; 
                    //System.out.println(obj.suitChar(i));
                    break;
                }
                if (maxCardsSuit == -1 || cnt[i] > cnt[maxCardsSuit]) maxCardsSuit = i;
            }
            String ans;
            if (points + rules567 < 14) ans = "PASS";
            else {
                if (points >= 16 && obj.allTrue(stopped)) ans = "BID NO-TRUMP";
                else ans = "BID " + obj.suitChar(maxCardsSuit);
            }
            System.out.println(ans);
            //System.out.println("points=" + points + ", rules567=" + rules567);
        }
        inp.close();
    }
}