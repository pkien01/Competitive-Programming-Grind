/*input
1900 3
2000 5
1950 2
101 1
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

    static int m, n;
    static int[] price, favour;

    static int solve() {
    	int[][] dp = new int[n + 1][m + 200 + 1];
    	for (int i = 0; i <= n; i++) Arrays.fill(dp[i], (int)-1e6);
    	dp[0][0] = 0;
    	int res = 0;
    	for (int i = 1; i <= n; i++) {
    		for (int j = 0; j <= m + 200; j++) {
    			dp[i][j] = dp[i - 1][j];
    			if (j >= price[i]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - price[i]] + favour[i]);
    			if (j > 2000 && j - 200 <= m) res = Math.max(res, dp[i][j]);
    			else if (j <= m) res = Math.max(res, dp[i][j]);
    		}
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	m = inp.nextInt(); n = inp.nextInt();
        	price = new int[n + 1]; favour = new int[n + 1];
        	for (int i = 1; i <= n; i++) {
        		price[i] = inp.nextInt();
        		favour[i] = inp.nextInt();
        	}
        	System.out.println(solve());
        }
        //out.close();
    }
}