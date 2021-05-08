/*input
12
1
2
3
4
5
6
7
8
9
10
39
691
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
    static final int max_n = (int)1e7;
    static int[] dp;
    static int[] ans;
    static void precompute() {
    	dp = new int[max_n + 1];
    	for (int i = 1; i <= max_n; i++) {
    		for (int j = i; j <= max_n; j += i) {
    			dp[j] += i;
    			if (dp[j] > max_n) {
    				dp[j] = max_n + 1;
    			} 
    		}
    	}
    	
    	
    	ans = new int[max_n + 1];
    	for (int i = 1; i <= max_n; i++) if (dp[i] <= max_n && ans[dp[i]] == 0) ans[dp[i]] = i;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        precompute();
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int c = inp.nextInt();
        	if (ans[c] == 0) {
        		System.out.println("-1");
        	}
        	else {
        		System.out.println(ans[c]);
        	}
        }
        
        //out.close();
    }
}
