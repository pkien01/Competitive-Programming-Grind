/*input
5
12 5
12 9
12 10
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
    static final int mod = (int)1e9 + 7;
    static ArrayList<Integer>[] dp;

    static int[] calc(int cur_m, int dig) {
    	ArrayList<Integer> cur = new ArrayList<>();
    	for (int i = 0; i < 10; i++) cur.add(0);
    	if (cur_m == 0) {
    		for (int i)
    		return res; 
    	}
    	if (dp[cur_m][dig] != null) return dp[cur_m][dig];

    	if (dig > 1) {
    		calc(cur_m - 1, dig - 1);
    	}
    	return calc(cur_m - 1, 9);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);


        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int[] cnt = new int[10];
        	long ans = 0;
        	while (n != 0) {
        		cnt[n % 10]++;
        		n /= 10;
        		ans++;
        	}
        	//System.out.println(Arrays.toString(cnt));
        	int m = inp.nextInt();
        	while (m-- > 0) {
        		ans = (ans + cnt[9]) % mod;
        		int[] next_cnt = cnt.clone();
        		for (int i = 0; i < 9; i++) {
        			if (cnt[i] > 0) {
        				next_cnt[i + 1] += cnt[i];
        				next_cnt[i] -= cnt[i];
        			}
        		} 
        		if (cnt[9] > 0) {
        			next_cnt[0] += cnt[9];
        			next_cnt[9] -= cnt[9]; 
        			next_cnt[1] += cnt[9];
        		}	
        		cnt = next_cnt.clone();
        		//System.out.println(Arrays.toString(cnt));
        	}
        	System.out.println(ans);
        }
        
        //out.close();
    }
}