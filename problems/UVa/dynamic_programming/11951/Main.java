/*input
1
5 6 15
10 1 10 20 10 10
30 1 1 5 1 1
50 1 1 20 1 1
10 5 5 10 5 1
40 10 90 1 10 10
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
    static int n, m, k;
    static int[][] arr;

    static void solve() {
    	int resArea = 0;
    	long resCost = 0L;
    	for (int x1 = 0; x1 < n; x1++) {
    		for (int x2 = x1; x2 < n; x2++) {
    			long curSum = 0; int curLen = 0;
    			for (int y = 0; y < m; y++) {
    				curSum += (long)arr[x2][y] - (x1 == 0? 0: arr[x1 - 1][y]);
    				curLen++;
    				while (curSum > k && curLen > 0) {
    					curSum -= (long)arr[x2][y - curLen + 1] - (x1 == 0? 0 : arr[x1 - 1][y - curLen + 1]);
    					curLen--;
    				}
    				if ((x2 - x1 + 1) * curLen > resArea) {
    					resArea = (x2 - x1 + 1) * curLen;
    					resCost = curSum;
    				}
    				else if ((x2 - x1 + 1) * curLen == resArea && curSum < resCost) {
    					resArea = (x2 - x1 + 1) * curLen;
    					resCost = curSum;
    				}
    			}
    		}
    	}
    	System.out.println(resArea + " " + resCost);
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); m = inp.nextInt(); k = inp.nextInt();
        	arr = new int[n][m];
        	for (int i = 0; i < n; i++) 
        		for (int j = 0; j < m; j++) 
        			arr[i][j] = inp.nextInt() + (i == 0? 0: arr[i - 1][j]);
        	
        	System.out.print("Case #" + test + ": ");
        	solve();
        }
        
        //out.close();
    }
}