/*input
3
6
10 100 50 30 80 10
50 10 10 15 20 10
4
30 20 20 10
20 30 40 50
3
80 80 80
15 25 20
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
    static int[] heights, widths;
    static void solve() {
    	int[] lis = new int[n], lds = new int[n];
    	int maxLis = 0, maxLds = 0;
    	for (int i = 0; i < n; i++) {
    		int curLis = 0, curLds = 0;
    		for (int j = 0; j < i; j++) {
    			if (heights[j] < heights[i]) curLis = Math.max(curLis, lis[j]);
    			else if (heights[j] > heights[i]) curLds = Math.max(curLds, lds[j]);
    		}
    		lis[i] = curLis + widths[i];
    		maxLis = Math.max(maxLis, lis[i]);
    		lds[i] = curLds + widths[i];
    		maxLds = Math.max(maxLds, lds[i]);
    	}
    	if (maxLis >= maxLds) System.out.format("Increasing (%d). Decreasing (%d).\n", maxLis, maxLds);
    	else System.out.format("Decreasing (%d). Increasing (%d).\n", maxLds, maxLis);
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
    	int numTests = inp.nextInt();
    	for (int test = 1; test <= numTests; test++) {
    		n = inp.nextInt();
    		heights = new int[n]; widths = new int[n];
    		for (int i = 0; i < n; i++) heights[i] = inp.nextInt();
    		for (int i = 0; i < n; i++) widths[i] = inp.nextInt();
    		System.out.print("Case " + test + ". "); solve();
    	}    
        
        //out.close();
    }
}