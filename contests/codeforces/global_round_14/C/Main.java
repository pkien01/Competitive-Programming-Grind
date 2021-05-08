/*input
2
5 2 3
1 2 3 1 2
4 3 3
1 1 2 3
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

    static int n, m, val;
    static int[] arr;

    static void solve() {
    	int prevSum = arr[0], curSum = 0, cnt = 1;
    	boolean ans = false;
    	int[] towerId = new int[n];
    	for (int i = 1; i < n;) {
    		curSum += arr[i];
    		towerId[i] = cnt;
    		if (curSum - prevSum < -val) i++;
    		else if (curSum - prevSum > val) {
    			ans = false;
    			break;
    		}
    		else {
    			prevSum = curSum;
    			curSum = 0;
    			cnt++;
    			i++;
    		}
    	}
    	System.out.println(Arrays.toString(towerId));
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); m = inp.nextInt(); val = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	solve();
        }
        
        //out.close();
    }
}