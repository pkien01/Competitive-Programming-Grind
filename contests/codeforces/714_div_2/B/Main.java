/*input
4
3
1 1 1
5
1 2 3 4 5
5
0 2 0 3 0
4
1 3 5 1
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
    static final long mod = (long)1e9 + 7;
    static int n;
    static int[] arr;
    static int solve() {
    	int sum_and = arr[0];
    	for (int i = 1; i < n; i++) sum_and &= arr[i];
    	TreeMap<Integer, Integer> cnt = new TreeMap<>();
    	for (int i = 0; i < n; i++) {
    		if (!cnt.containsKey(arr[i])) cnt.put(arr[i], 0);
    		cnt.put(arr[i], cnt.get(arr[i]) + 1);
    	}
    	int cnt_sum_and = !cnt.containsKey(sum_and)? 0 : cnt.get(sum_and);
    	long numPerm = 1L;
    	for (int i = 1; i <= n - 2; i++) numPerm = numPerm * i % mod;
    	long ans = (long)cnt_sum_and * (cnt_sum_and - 1) % mod * numPerm % mod;
    	return (int)ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}