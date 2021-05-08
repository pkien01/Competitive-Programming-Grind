/*input
20 2
20 2
0 0
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
    static final int mod = 1000000;
    static int[][] dp;
    static void precompute() {
    	dp = new int[101][101];
    	dp[0][0] = 1;
    	for (int x = 0; x <= 100; x++) 
    		for (int i = 1; i <= 100; i++) 
    			for (int y = 0; y <= x; y++) dp[x][i] = (dp[x][i] + dp[x - y][i - 1]) % mod;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        precompute();
        while (true) {
        	int n = inp.nextInt(), k = inp.nextInt();
        	if (n == 0 && k == 0) break;
        	System.out.println(dp[n][k]);
        }
        
        //out.close();
    }
}