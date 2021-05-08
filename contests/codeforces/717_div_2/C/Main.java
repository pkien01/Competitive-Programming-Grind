/*input
4
6 3 9 12
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
    static final int inf = (int)2e8;
   	static int n, sum;
   	static int[] arr;
   	static int[][] dp;

   	static int solve(int idx, int rmv) {
   		if (idx == n) return 0;
   		if (dp[idx][rmv] != -1) return dp[idx][rmv];
   		int nxt = solve(idx + 1, rmv);
   		int res = Math.min(Math.abs(nxt + arr[idx]), Math.abs(nxt - arr[idx]));
   		if (rmv > 0) res = Math.min(res, solve(idx + 1, rmv - 1));
   		return res;
   	}
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        n = inp.nextInt(); arr = new int[n]; sum = 0;
        for (int i = 0; i < n; i++) {
        	arr[i] = inp.nextInt();
        	sum += arr[i];
        }
        /*
        int ans = n + 1;
        for (int i = 0; i < n; i++) {
        	if (solve(0, i) != 0) {
        		ans = i;
        		break;
        	}
        }*/
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println(solve(1, 0));   
        //out.close();
    }
}