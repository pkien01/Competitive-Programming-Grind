/*input
5 
1 -1 0 0 -4 
2 3 -2 -3 2 
4 1 -1 5 0 
3 -2 1 -3 2 
-3 2 4 1 -4 
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
    static int[][] arr, sum;

    static int subRec(int x1, int y1, int x2, int y2) {
    	if (x1 > x2 || y1 > y2) return 0;
    	return sum[x2][y2] - sum[x2][y1 - 1] - sum[x1 - 1][y2] + sum[x1 - 1][y1 - 1];
    }
    static int wrapRec(int x1, int y1, int x2, int y2) {
    	int res = sum[n][n];
    	if (x1 > 1 || x2 < n) res -= subRec(x1, 1, x2, n); 
    	if (y1 > 1 || y2 < n) res -= subRec(1, y1, n, y2);
    	if ((x1 > 1 || x2 < n) && (y1 > 1 || y2 < n)) res += subRec(x1, y1, x2, y2);
    	return res;
    }
    static int solve() {
    	sum = new int[n + 1][n + 1];
    	for (int i = 1; i <= n; i++) 
        		for (int j = 1; j <= n; j++) sum[i][j] = arr[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];

    	int res = sum[n][n];
    	for (int x1 = 1; x1 <= n; x1++) {
    		for (int y1 = 1; y1 <= n; y1++) {
    			for (int x2 = x1; x2 <= n; x2++) {
    				for (int y2 = y1; y2 <= n; y2++) {
    					res = Math.max(res, subRec(x1, y1, x2, y2));
    					res = Math.max(res, wrapRec(x1, y1, x2, y2));
    					if (y1 != y2) res = Math.max(res, subRec(x1, 1, x2, y1) + subRec(x1, y2, x2, n));
    					if (x1 != x2) res = Math.max(res, subRec(1, y1, x1, y2) + subRec(x2, y1, n, y2));
    					//System.out.format("(%d, %d, %d, %d): %d\n", x1, y1, x2, y2, wrapRec(x1, y1, x2, y2));
    				}
    			}
    		}
    	}		
    	//System.out.println("lol " + wrapRec(2, 3, 2, 3));
    	return res;
    }

    static int[][] wrapSum;
    static int solve2() {
    	wrapSum = new int[2*n + 1][2*n + 1];

    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			wrapSum[i][j] = arr[i][j];
    			wrapSum[i + n][j] = arr[i][j];
    			wrapSum[i][j + n] = arr[i][j];
    			wrapSum[i + n][j + n] = arr[i][j];
    		}
    	}
    	
    	//for (int i = 1; i <= 2*n; i++) System.out.println(Arrays.toString(wrapSum[i]));
    	for (int i = 1; i <= 2*n; i++)
    		for (int j = 1; j <= 2*n; j++) 
    			wrapSum[i][j] += wrapSum[i - 1][j] + wrapSum[i][j - 1] - wrapSum[i - 1][j - 1];


    	int res = (int)-1e9;
    	for (int x1 = 1; x1 <= n; x1++) 
    		for (int x2 = x1; x2 < x1 + n; x2++) 
    			for (int y1 = 1; y1 <= n; y1++) 
    				for (int y2 = y1; y2 < y1 + n; y2++)
    					res = Math.max(res, wrapSum[x2][y2] - wrapSum[x2][y1 - 1] - wrapSum[x1 - 1][y2] + wrapSum[x1 - 1][y1 - 1]);
    	return res;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	arr = new int[n + 1][n + 1];
        	for (int i = 1; i <= n; i++) 
        		for (int j = 1; j <= n; j++) arr[i][j] = inp.nextInt();
        	System.out.println(solve2());
        }
        //out.close();
    }
}