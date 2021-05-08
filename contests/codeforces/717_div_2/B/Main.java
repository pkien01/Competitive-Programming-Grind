/*input
2
3
0 2 2
4
2 3 1 10
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

    static boolean solve() {
    	int[] prefXor = new int[n + 1];
    	for (int i = 1; i <= n; i++) prefXor[i] = prefXor[i - 1] ^ arr[i - 1];
    	for (int i = 1; i < n; i++) {
    		if (prefXor[i] == (prefXor[n] ^ prefXor[i])) return true;
    		for (int j = i + 1; j < n; j++)
    			if (prefXor[i] == (prefXor[j] ^ prefXor[i]) && (prefXor[j] ^ prefXor[i]) == (prefXor[n] ^ prefXor[j])) return true;
    	}
    	return false;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	System.out.println(solve()? "YES": "NO");
        }
        //out.close();
    }
}