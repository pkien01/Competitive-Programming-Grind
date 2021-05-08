/*input
5
3
2 3 1
4
4 2 3 1
4 
2 4 3 1
4
2 3 4 1
4
2 3 1 4
*/
import java.util.*;
import java.io.*;

public class Solution {
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
    static int solve() {
    	int res = 0;
    	for (int i = 0; i < n - 1; i++) {
    		int minIdx = i;
    		for (int j = i + 1; j < n; j++) 
    			if (arr[j] < arr[minIdx]) minIdx = j;
    		res += minIdx - i + 1;
    		for (int l = i, r = minIdx; l < r; l++, r--) {
    			int tmp = arr[l]; arr[l] = arr[r]; arr[r] = tmp;
    		}
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println("Case #" + test + ": " + solve());
        }
        
        //out.close();
    }
}