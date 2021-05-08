/*input
1
3
1
2
3
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

    // arr[l1] > arr[l2] > ... > arr[m] > arr[r1] > arr[r2] > ... 
    //     l1  >     l2  > ... >     m  <     r1  <     r2  < ...
    //    ans = length(l) + length(r) + 1 = lis(m, n - 1) + lds(m, n - 1) - 1
    static int solve() {
    	int[] lis = new int[n], lds = new int[n];
    	int res = 0;

    	for (int i = n - 1; i >= 0; i--) {
    		int curLis = 1, curLds = 1;
    		for (int j = n - 1; j > i; j--) {
    			if (arr[j] < arr[i]) curLds = Math.max(curLds, lds[j] + 1);
    			else curLis = Math.max(curLis, lis[j] + 1);
    		}
    		res = Math.max(res, curLis + curLds - 1);
    		lis[i] = curLis; lds[i] = curLds;
    	}

    	return res;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println(solve());
        }
        //out.close();
    }
}