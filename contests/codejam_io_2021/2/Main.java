/*input
3
2
2 3
2
5 1
1
2
*/
import java.util.*;
import java.io.*;

public class Main {

    static int n, len;
    static int[] block; 
    static ArrayList<Integer> arr;
    static int[][] dp;

    static boolean solve(int idx, int prev) {
    	if (idx == len) return true;
    	if (dp[idx][prev] != -1) return dp[idx][prev] == 1;
    	boolean res = false;
    	if (arr.get(idx) == 1) {
    		for (int cur = prev + 1; cur < 26; cur++) {
    			if (solve(idx + 1, cur)) {
    				res = true;
    				break;
    			}
    		}
    	}
    	else if (arr.get(idx) == -1) {
    		for (int cur = 0; cur < prev; cur++) {
    			if (solve(idx + 1, cur)) {
    				res = true;
    				break;
    			}
    		}
    	}
    	dp[idx][prev] = res? 1: 0;
    	return res;
    }

    static StringBuilder ans;
    static void trace(int idx, int prev) {
		if (idx == len) return;
    	if (arr.get(idx) == 1) {
    		for (int cur = prev + 1; cur < 26; cur++) {
    			if (solve(idx + 1, cur)) {
    				ans.append((char)(cur + 'A'));
    				trace(idx + 1, cur);
    				return;
    			}
    		}
    	}
    	else if (arr.get(idx) == -1) {
    		for (int cur = 0; cur < prev; cur++) {
    			if (solve(idx + 1, cur)) {
    				ans.append((char)(cur + 'A'));
    				trace(idx + 1, cur);
    				return;  		
    			}
    		}
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner scan = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
    	    
        int numTests = scan.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = scan.nextInt();
        	block = new int[n];
        	for (int i = 0; i < n; i++) block[i] = scan.nextInt();
    		
    		arr = new ArrayList<>(); arr.add(0);
        	for (int i = 0; i < n; i++) {
        		if (i % 2 == 0) {
        			for (int j = 0; j < block[i]; j++) arr.add(1);      
        		}
        		else {
        			for (int j = 0; j < block[i]; j++) arr.add(-1);        		
        		}
        	}
        	len = arr.size();
        	dp = new int[len][26];
        	for (int i = 0; i < len; i++) Arrays.fill(dp[i], -1);
        	ans = new StringBuilder(); ans.append('A');
        	trace(1, 0);

        	System.out.println("Case #" + test + ": " + ans);
        }
        //out.close();
    }
}