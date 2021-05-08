/*input
2
5
38 111 102 111 177
8
276 284 103 439 452 276 452 398
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
    static int solve(int n, int[] arr) {
    	int ans = 0;
    	for (int i = 0; i < arr.length; i++) {
    		int cur = arr[i];
    		for (int j = 0; j < i; j++) ans += arr[j] <= cur? 1 : 0;
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTest = inp.nextInt();
        while (numTest-- > 0) {
        	int n = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println(solve(n, arr));
        }
    }
}