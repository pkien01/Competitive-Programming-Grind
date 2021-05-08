/*input
5 3
1 2 3 4 5
3 2
4 78 9
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

    static int n, m;
    static int[] arr;
    static int maxElems, sumElems;

    static boolean canFill(int maxCap) {
    	int curSum = 0, containers = 0;
    	for (int i = 0; i < n; i++) {
    		if (curSum + arr[i] > maxCap) {
    			curSum = 0;
    			containers++;
    			if (containers > m) return false;
    		}	
    		curSum += arr[i];
    	}
    	containers++;
    	return containers <= m;
    }

    static int solve() {
    	int l = maxElems, r = sumElems;
    	while (l <= r) {
    		int mid = (l + r) >> 1;
    		if (canFill(mid)) r = mid - 1;
    		else l = mid + 1;
    	} 
    	return r + 1;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); m = inp.nextInt();
        	arr = new int[n];
        	maxElems = 0; sumElems = 0;
        	for (int i = 0; i < n; i++) {
        		arr[i] = inp.nextInt();
        		maxElems = Math.max(maxElems, arr[i]);
        		sumElems += arr[i];
        	}
        	System.out.println(solve());
        }
        
        //out.close();
    }
}