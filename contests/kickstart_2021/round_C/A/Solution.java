/*input
2
10
125
*/
import java.util.*;
import java.io.*;

public class Solution {
    static void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming/io";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static long maxGold;

    static long solve() {
    	long cnt = 0;
    	for (long i = 2; i * (i - 1) / 2 <= maxGold; i++) {
    		if ((maxGold - i * (i - 1) / 2) % (i - 1) == 0) {
    			cnt++;
    		}
    	}
    	return cnt;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	maxGold = inp.nextLong();
        	System.out.println("Case #" + test + ": " + solve());
        }
        
        //out.close();
    }
}