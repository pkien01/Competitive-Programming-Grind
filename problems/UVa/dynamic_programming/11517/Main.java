/*input
1
1400
3
500
1000
2000
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

    final static int inf = (int)2e5;
    static int n, sum, price;
    static int[] arr;
    static int[][] dp;

    static int solve(int idx, int val) {
    	if (val < 0) return inf;
    	if (val == 0) return 0;
    	if (idx >= n) return inf;
    	if (dp[idx][val] != -1) return dp[idx][val];
    	int res = solve(idx + 1, val);
    	if (arr[idx] <= val) res = Math.min(res, solve(idx + 1, val - arr[idx]) + 1);
    	dp[idx][val] = res;
    	return res;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	price = inp.nextInt();
        	n = inp.nextInt();
        	arr = new int[n];
        	sum = 0;
        	for (int i = 0; i < n; i++) {
        		arr[i] = inp.nextInt();
        		sum += arr[i];
        	}
        	dp = new int[n][sum + 1];
        	for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        	for (int i = price; i <= sum; i++) {
        		if (solve(0, i) < inf) {
        			System.out.println(i + " " + solve(0, i));
        			break;
        		}
        	}
        }
        
        //out.close();
    }
}