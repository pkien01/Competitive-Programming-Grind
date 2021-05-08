/*input
4 1 4 2 3
5 1 4 2 -1 6
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    boolean solve(int n, int[] arr) {
    	boolean[] hasDiff = new boolean[arr.length];
    	for (int i = 1; i < n; i++) {
    		int diff = Math.abs(arr[i] - arr[i - 1]);
    		if (diff < 1 || diff >= n || hasDiff[diff]) return false;
    		hasDiff[diff] = true;
    	}
    	for (int i = 1; i < n; i++)
    		if (!hasDiff[i]) return false;
    	return true;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println(obj.solve(n, arr)? "Jolly" : "Not jolly");
        }
    }
}