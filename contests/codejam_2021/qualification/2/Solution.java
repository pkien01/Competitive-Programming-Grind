/*input
4
2 3 CJ?CC?
4 2 CJCJ
1 3 C?J
2 5 ??J???
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
    
  	static boolean canFill(String s1, String s2) {
  		for (int i = 0; i < 3; i++) 
  			if (s1.charAt(i) != s2.charAt(i) && s1.charAt(i) != '?' && s2.charAt(i) != '?')
  				return false;
  		return true;
  	}
  	static int cj, jc, n;
  	static int[][] dp;
  	static String s;
  	static int cost(int prev, int cur) {
  		if (prev == 0 && cur == 1) return cj;
  		if (prev == 1 && cur == 0) return jc;
  		return 0;
  	}
  	static int solve(int idx, int prev) {
  		if (idx == n) return 0;
  		if (dp[idx][prev] != -1) return dp[idx][prev];
 		int res = 0;
  		if (s.charAt(idx) == '?') 
  			res = Math.min(solve(idx + 1, 0) + (idx == 0? 0 : cost(prev, 0)), solve(idx + 1, 1) + (idx == 0? 0 : cost(prev, 1)));

  		else {
  			int cur = s.charAt(idx) == 'C'? 0 : 1;
  			res = solve(idx + 1, cur) + (idx == 0? 0 : cost(prev, cur));
  		}
  		dp[idx][prev] = res;
  		return res;
  	}
    public static void main(String[] args) {     

        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	cj = inp.nextInt(); jc = inp.nextInt(); 
        	s = inp.next();
        	n = s.length();
        	dp = new int[s.length() + 1][2];
        	for (int i = 0; i <= s.length(); i++)
        		for (int j = 0; j < 2; j++) dp[i][j] = -1;
        	System.out.println("Case #" + test + ": " + solve(0, 0));
        }
        
        //out.close();
    }
}