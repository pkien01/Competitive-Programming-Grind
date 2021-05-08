/*input
-7
10
9
2
3
8
8
6
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
    static ArrayList<Integer> arr;

    static void solve() {
    	// smallest ending value for all length-i LISs found so far
    	ArrayList<Integer> lisVals = new ArrayList<>(), lisIds = new ArrayList<>();
    	ArrayList<Integer>  prev = new ArrayList<>();
    	for (int i = 0; i < n; i++) prev.add(-1);

    	int lisLen = 0, lisEnd = 0;
    	for (int i = 0; i < n; i++) {
    		int upperBound = Collections.binarySearch(lisVals, arr.get(i));
    		if (upperBound < 0) upperBound = -upperBound -1;
    		if (upperBound == lisVals.size()) {
    			lisVals.add(arr.get(i));
    			lisIds.add(i);
    		}
    		else {
    			lisVals.set(upperBound, arr.get(i));
    			lisIds.set(upperBound, i);
    		}
    		if (upperBound > 0) prev.set(i, lisIds.get(upperBound - 1));
    		if (upperBound + 1 >= lisLen) {
    			lisLen = upperBound + 1;
    			lisEnd = i;
    		}
    	}
    	
    	System.out.println(lisLen + "\n-");
    	ArrayList<Integer> ans = new ArrayList<>();
    	for (int i = lisEnd; i != -1; i = prev.get(i)) ans.add(arr.get(i));
    	for (int i = ans.size() - 1; i >= 0; i--) System.out.println(ans.get(i));
    	
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        arr = new ArrayList<>();
        while (inp.hasNextInt()) arr.add(inp.nextInt());
        n = arr.size();
        solve();
        //out.close();
    }
}