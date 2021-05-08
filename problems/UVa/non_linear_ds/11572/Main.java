/*input

*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
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
    int solve(int n, int[] arr) {
    	Map<Integer, Integer> mp = new TreeMap<>();
    	int res = 0;
    	for (int i = 0, j = 0; i < n; i++) {
    		if (mp.containsKey(arr[i])) {
    			//System.out.format("[%d, %d]\n", j, i - 1);
    			res = Math.max(res, i - j);
    			j = Math.max(j, mp.get(arr[i]) + 1);
    		}
    		else if (i == n - 1) {

    			//System.out.format("[%d, %d]\n", j, i);
    			res = Math.max(res, i - j + 1);
    		}
    		mp.put(arr[i], i);
    	}
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	/*if (numTests == 0) {
        		System.out.println(arr.length + " " + Arrays.toString(arr));
        	}*/
        	System.out.println(obj.solve(n, arr));	
        }
    }
}