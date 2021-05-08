/*input
2
3
1 5 4
2
100 10000
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
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	boolean ans = false;
        	for (int i = 1; i < n; i++) {
        		if (arr[i] != arr[0]) {
        			ans = true; break;
        		}
        	}
        	System.out.println(ans? "YES" : "NO");
        }
        
        //out.close();
    }
}