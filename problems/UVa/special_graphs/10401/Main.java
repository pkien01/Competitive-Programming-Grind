/*input
??
?
1??
??2
??????????????A
??????????????F
??????????????1
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

    static int n;
    static String s;
    static long[][] dp;

    static long dfs(int row, int prevCol) {
    	if (row == n) return 1L;
    	if (dp[row][prevCol] != -1) return dp[row][prevCol];
    	long res = 0L;
    	if (s.charAt(row) == '?') {
    		for (int col = 0; col < n; col++)
    			if (prevCol == n || Math.abs(col - prevCol) > 1) res += dfs(row + 1, col);
    	}
    	else {
    		int col = Character.isDigit(s.charAt(row))? (int)s.charAt(row) - '0' : (int)s.charAt(row) - 'A' + 10;
    		col--;
    		if (prevCol == n || Math.abs(col - prevCol) > 1) res = dfs(row + 1, col);
    	}
    	dp[row][prevCol] = res;
    	return res;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNext()) {
        	s = inp.next();
        	n = s.length();
        	dp = new long[n + 1][n + 1];
        	for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1L);
        	System.out.println(dfs(0, n));
        }
        
        //out.close();
    }
}