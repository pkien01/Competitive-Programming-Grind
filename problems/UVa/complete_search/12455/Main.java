/*input
4
25
4
10 12 5 7
925
10
45 15 120 500 235 58 6 12 175 70
120
5
25 25 25 25 25
0
2
13 567
*/
import java.util.*;
import java.io.*;

public class Main {
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
    static boolean solve(int len, int n, int[] bars) {
    	for (int mask = 0; mask < (1 << n); mask++) {
    		int sum = 0;
    		for (int i = 0; i < n; i++) {
    			sum += (mask >> i & 1) * bars[i];
    			if (sum > len) break;
    			if (sum == len) return true;
    		}
    		//if (sum == len) return true;
    	}
    	return false;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests =inp.nextInt();
        while (numTests-- > 0) {
        	int len = inp.nextInt();
        	int n = inp.nextInt();
        	int[] bars = new int[n];
        	for (int i = 0; i < n; i++) bars[i] = inp.nextInt();
        	System.out.println(solve(len, n, bars)? "YES" : "NO");
        }
    }
}