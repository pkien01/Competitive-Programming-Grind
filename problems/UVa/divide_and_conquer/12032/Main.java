/*input
2
5
1 6 7 11 13
4
3 9 10 14
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
    static int[] arr;

    static boolean canReach(int k) {
    	for (int i = 1; i <= n; i++) {
    		if (arr[i] - arr[i - 1] > k) return false;
    		if (arr[i] - arr[i - 1] == k) k--;
    	}
    	return true;
    }
    static int solve() {
    	int l = 0;
    	for (int i = 1; i <= n; i++) l = Math.max(l, arr[i] - arr[i - 1]);
    	int r = l + n;
    	while (l <= r) {
    		int mid = (l + r) >> 1;
    		if (canReach(mid)) r = mid - 1;
    		else l = mid + 1;
    	}
    	return r + 1;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n  = inp.nextInt();
        	arr = new int[n + 1]; arr[0] = 0;
        	for (int i = 1; i <= n; i++) arr[i] = inp.nextInt();
        	System.out.println("Case " + test + ": " + solve());
        }	
        
        //out.close();
    }
}