/*input
3 10 5 2
6 7 5 6 9
10 9 10 10 8
0 0 0 0
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

    static int n, maxPay, teaCharge, k;
    static int[] price;
    static int[] favour;

    static final int inf = (int)1e6;

    static int totalCharge(int curPay) {
    	return curPay + teaCharge * n + (int)Math.ceil(0.1 * (curPay + teaCharge*n));
    }
    static double solve() {
    	int[][][] dp = new int[k + 1][2*n + 1][(maxPay - teaCharge) *n + 1];
    	
    	for (int i = 0; i <= k; i++)
    		for (int j = 0; j <= 2*n; j++)
    			for (int p = 0; p <= (maxPay - teaCharge) *n; p++) dp[i][j][p] = -inf;
    	
    	int res = 0;
    	dp[0][0][0] = 0;

    	for (int i = 1; i <= k; i++) {
    		dp[i][0][0] = 0;
    		for (int j = 1; j <= 2*n; j++) {
    			for (int p = 0; totalCharge(p) <= maxPay*n; p++) {
    				int curDp = dp[i - 1][j][p];
    				if (p >= price[i])
    					curDp = Math.max(curDp, dp[i - 1][j - 1][p - price[i]] + favour[i]);
    				if (j >= 2 && p >= 2*price[i]) 
    					curDp = Math.max(curDp, dp[i - 1][j - 2][p - 2*price[i]] + 2*favour[i]);
    				dp[i][j][p] = curDp;

    				res = Math.max(res, curDp);
    			}
    			//System.out.println(Arrays.toString(dp[i][j]));
    		}
    	}

    	return (double)res / n;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); 
        	maxPay = inp.nextInt(); 
        	teaCharge = inp.nextInt(); 
        	k = inp.nextInt();
        	if (n == 0 && maxPay == 0 && teaCharge == 0 && k == 0) break;
        	n++;

        	price = new int[k + 1];
        	favour = new int[k + 1];
        	for (int i = 1; i <= k; i++) {
        		price[i] = inp.nextInt();
        		for (int j = 1; j <= n; j++) favour[i] += inp.nextInt();
        	}

        	System.out.format("%.2f\n", solve());
        }
        
        //out.close();
    }
}