/*input
4
7
1 2 1 2 1 2 1
6 8 3 1 5 1 5
10
1 1 1 2 2 2 2 3 3 3
3435 3014 2241 2233 2893 2102 2286 2175 1961 2567
6
3 3 3 3 3 3
5 9 6 7 9 7
1
1
3083
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
    static int[] id, val;
    static ArrayList<Integer>[] arr;

    static long compute(int k) {
    	long res = 0;
    	for (int i = 0; i < n; i++)
    		if (arr[i].size() > 0 && arr[i].size() >= k) res += arr[i].get(arr[i].size() - arr[i].size() % k - 1);
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	id = new int[n];
        	for (int i = 0; i < n; i++) id[i] = inp.nextInt();
        	val = new int[n];
        	for (int i = 0; i < n; i++) val[i] = inp.nextInt();

        	arr = new ArrayList[n];
        	for (int i = 0; i < n; i++) arr[i] = new ArrayList<>();
        	for (int i = 0;i < n; i++) arr[id[i] - 1].add(val[i]);
        	for (int i = 0; i <n; i++) Collections.sort(arr[i], Collections.reverseOrder());
        	
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < arr[i].size(); j++) arr[i].set(j, (j > 0? arr[i].get(j - 1) : 0) + arr[i].get(j));
        	}
        	
        	long[] ans = new long[n];
        	for (int i = 1; i <= n; i++) {
        		if (ans[i - 1] == 0L) {
        			ans[i - 1] = compute(i);
        			for (int j = i + i; j < n; j += i) ans[j - 1] = ans[i - 1];
        		}	
        	}
        	for (int i = 0; i < n; i++) System.out.print(ans[i] + (i == n - 1? "\n" : " "));

        }
        //out.close();
    }
}