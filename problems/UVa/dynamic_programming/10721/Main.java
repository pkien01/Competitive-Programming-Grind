/*input
7 4 3
7 4 2
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
    static int n, k, m;
    static long solve() {
    	long[][] dp = new long[n + 1][k + 1];
    	dp[0][0] = 1;
    	for (int i = 1; i <= n; i++) 
    		for (int j = 1; j <= k && j <= i; j++) 
				for (int l = 1; l <= m && l <= i; l++) dp[i][j] += dp[i - l][j - 1];
    	
    	return dp[n][k];
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); k = inp.nextInt(); m = inp.nextInt();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}