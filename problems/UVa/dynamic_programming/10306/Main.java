/*input
3
2 5
0 2
2 0
3 20
0 2
2 0
2 1
3 5
3 0
0 4
5 5
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

    final static int inf = (int)2e6;
    static int n, target;
    static int[] a, b;
    static int[][] dp;

   	static int solve(int sum_a, int sum_b) {
    	if (sum_a > target || sum_b > target || sum_a * sum_a + sum_b * sum_b > target * target) return inf;
    	if (sum_a * sum_a + sum_b * sum_b == target * target) return 0;
    	if (dp[sum_a][sum_b] != -1) return dp[sum_a][sum_b];
    	int res = inf;
    	for (int i = 0; i < n; i++) 
    		if (a[i] != 0 || b[i] != 0) res = Math.min(res, solve(sum_a + a[i], sum_b + b[i]) + 1);
    	dp[sum_a][sum_b] = res;
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); target = inp.nextInt();
        	a = new int[n]; b = new int[n];
        	for (int i = 0; i < n; i++) {
        		a[i] = inp.nextInt(); b[i] = inp.nextInt();
        	}

        	dp = new int[target + 1][target + 1];
        	for (int sum_a = 0; sum_a <= target; sum_a++)
        		for (int sum_b = 0; sum_b <= target; sum_b++) dp[sum_a][sum_b] = -1;

        	int ans = solve(0, 0);

        	System.out.println(ans >= inf? "not possible": ans);
        }
        
        //out.close();
    }
}