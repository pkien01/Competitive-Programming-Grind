/*input
6
104 943872923 6589 889921234 1000000000 69
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

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        Arrays.sort(arr);

    	long ans = (long)1e16;
    	for (int idx = 0; idx <n; idx++) {
    		ArrayList<Integer> sol = new ArrayList<>();
  
    		for (int i = 0; i < idx; i++) sol.add(arr[i]);
    		for (int i = idx; i < n; i++) sol.add(arr[i]);
    	
    		int curMax = 0, curMin = (int)1e9 + 7;
    		long sum = 0;
    		for (int i = 0; i < n; i++) {
    			curMax = Math.max(curMax, sol.get(i));
    			curMin = Math.min(curMin, sol.get(i));
    			sum += curMax - curMin;
    		}
    		ans = Math.min(ans, sum);
    	}

    	for (int idx = 0; idx <n; idx++) {
    		ArrayList<Integer> sol = new ArrayList<>();
  
    		for (int i = 0; i < idx; i++) sol.add(arr[i]);
    		for (int i = n - 1; i >= idx; i--) sol.add(arr[i]);
    	
    		int curMax = 0, curMin = (int)1e9 + 7;
    		long sum = 0;
    		for (int i = 0; i < n; i++) {
    			curMax = Math.max(curMax, sol.get(i));
    			curMin = Math.min(curMin, sol.get(i));
    			sum += curMax - curMin;
    		}
    		ans = Math.min(ans, sum);
    	}

    	for (int idx = 0; idx <n; idx++) {
    		ArrayList<Integer> sol = new ArrayList<>();
  
    		for (int i = idx - 1; i >= 0; i--) sol.add(arr[i]);
    		for (int i = idx; i < n; i++) sol.add(arr[i]);
    	
    		int curMax = 0, curMin = (int)1e9 + 7;
    		long sum = 0;
    		for (int i = 0; i < n; i++) {
    			curMax = Math.max(curMax, sol.get(i));
    			curMin = Math.min(curMin, sol.get(i));
    			sum += curMax - curMin;
    		}
    		ans = Math.min(ans, sum);
    	}
    	for (int idx = 0; idx <n; idx++) {
    		ArrayList<Integer> sol = new ArrayList<>();
  
    		for (int i = idx - 1; i >= 0; i--) sol.add(arr[i]);
    		for (int i = n - 1; i >= idx; i--) sol.add(arr[i]);
    	
    		int curMax = 0, curMin = (int)1e9 + 7;
    		long sum = 0;
    		for (int i = 0; i < n; i++) {
    			curMax = Math.max(curMax, sol.get(i));
    			curMin = Math.min(curMin, sol.get(i));
    			sum += curMax - curMin;
    		}
    		ans = Math.min(ans, sum);
    	}
    	System.out.println(ans);
    }
}