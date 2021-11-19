/*input
2
1 200.1 100
1
0
5
3
3
3
3
3
2 300.23 100.17
1
2
0 4
4 0
4
7 15
20 5
3 10
4 10
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

    static int n,  m;
    static double invest, pay;
    static int[] bind;
    static double[][] change, rate;
    static double[][][] dp;

    static double solve(int idx, int type, int rem) { // 100 x 20 x 60
    	if (idx == m) return invest;
    	if (dp[idx][type][rem] != -1) return dp[idx][type][rem];

    	double res = 1e9;
    	if (rem == 0) {
    		for (int new_type = 0; new_type < n; new_type++) {
    			double new_amount = (solve(idx + 1, new_type, bind[new_type] - 1) + change[type][new_type]) * rate[idx][new_type] - pay;
    			res = Math.min(res, new_amount);
    		}
    	}
    	else res = solve(idx + 1, type, rem - 1) * rate[idx][type] - pay;

    	dp[idx][type][rem] = res;
    	return dp[idx][type][rem];
    }

    static ArrayList<Integer> res_types;
    static double res_amount;
    static void trace(int idx, int type, int rem) {
    	if (idx == m) return;
    	if (rem == 0) {
    		for (int new_type = 0; new_type < n; new_type++) {
    			double new_amount = (solve(idx + 1, new_type, bind[new_type] - 1) +  change[type][new_type]) * rate[idx][new_type] - pay;
    			if (solve(idx, type, rem) == new_amount) {
    				if (new_amount > 0) { 
    					res_types.add(type);
    					res_amount += Math.min(pay, new_amount);
    				}
    				trace(idx + 1, new_type, bind[new_type] - 1);
    				break;
    			}
    		}
    	}
    	else {
    		double new_amount = solve(idx + 1, type, rem - 1) * rate[idx][type] - pay;
    		if (new_amount > 0) { 
    			res_types.add(type);
    			res_amount += Math.min(pay, new_amount);
    		}
    		trace(idx + 1, type, rem - 1);
    	}
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); invest = inp.nextDouble(); pay = inp.nextDouble();

        	bind = new int[n];
        	for (int i = 0; i < n; i++) bind[i] = inp.nextInt();

        	change = new double[n][n];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < n; j++) change[i][j] = inp.nextDouble();

        	m = inp.nextInt();
        	rate = new double[m][n];
        	for (int i = 0; i < m; i++) 
        		for (int j = 0; j < n; j++) rate[i][j] = inp.nextInt();

        	dp = new double[m][n][61];
        	for (int i = 0; i < m; i++)
        		for (int j = 0; j < n; j++) Arrays.fill(dp[i][j], -1);

        	
        	double ans_amount = 1e9;
        	ArrayList<Integer> ans_types = new ArrayList<>();
        	for (int type = 0; type < n; type++) {
        		res_amount = 0;
        		res_types = new ArrayList<>();
   				trace(0, type, bind[type]);
   				if (ans_amount > res_amount) {
   					ans_amount = res_amount;
   					ans_types = res_types;
   				}
        	}
        	System.out.println(ans_amount);
        }	
        
        //out.close();
    }
}