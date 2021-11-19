/*input
2
30
10 10
60 60
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

    static int target;
    static int win, draw;

    static double[][][] dp;

    static double solve(int r, int p, int s) {
    	int n = r + p + s;
    	if (n >= 60) return 0.0;
    	if (dp[r][p][s] >= 0) return dp[r][p][s];
    	double pr = 1.0 / 3, pp = 1.0 / 3, ps = 1.0 / 3;
    	if (n != 0) {
    		pr = (double)s / n; pp = (double)r / n; ps = (double)p / n;
    	}
    	double move_r = (1 - ps - pr) * solve(r + 1, p, s) + ps * win + pr * draw;  
    	double move_p = (1 - pr - pp) * solve(r, p + 1, s) + pr * win + pp * draw;
    	double move_s = (1 - pp - ps) * solve(r, p, s + 1) + pp * win + ps * draw;
    	dp[r][p][s] = Math.max(move_r, Math.max(move_p, move_s));
    	return dp[r][p][s];
    }

    static boolean approx(double x, double y) {
    	return Math.abs(x - y) < 1e-6;
    }
    static char[] ans;
    static void trace(int r, int p, int s) {
    	int n = r + p + s;
    	if (n >= 60) return;
    	double pr = 1.0 / 3, pp = 1.0 / 3, ps = 1.0 / 3;
    	if (n != 0) {
    		pr = (double)s / n; pp = (double)r / n; ps = (double)p / n;
    	}
    	double move_r = (1 - ps - pr) * solve(r + 1, p, s) + ps * win + pr * draw;  
    	double move_p = (1 - pr - pp) * solve(r, p + 1, s) + pr * win + pp * draw;
    	double move_s = (1 - pp - ps) * solve(r, p, s + 1) + pp * win + ps * draw;

    	double cur = solve(r, p, s);

    	if (approx(cur, move_r)) {
    		ans[n] = 'R'; 
    		trace(r + 1, p, s);
    	}
    	else if (approx(cur, move_p)) {
    		ans[n] = 'P';
    		trace(r, p + 1, s);
    	}
    	else {
    		ans[n] = 'S';
    		trace(r, p, s + 1);
    	}
    }
    static String solve() {
    	for (int i = 0; i <= 60; i++)
    		for (int j = 0; j <= 60; j++) Arrays.fill(dp[i][j], -1.0);
    	
    	System.out.println(solve(0, 0, 0));
    	ans = new char[60];
    	trace(0, 0, 0);
    	return String.valueOf(ans);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        dp = new double[61][61][61];

        int numTests = inp.nextInt();
        target = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	win = inp.nextInt(); draw = inp.nextInt();
        	System.out.println("Case #" + test + ": " + solve());
        }
        
        //out.close();
    }
}