/*input
5
10
0111010000
0100101100
4
0000
0000
3
001
000
12
010101010101
100110011010
6
000111
110100
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
    static String a, b;

    static boolean solve() {
    	int cntPref = 0;
    	boolean allDiff = true, allEqual = true;
    	for (int i = 0; i < n; i++) {
    		cntPref += a.charAt(i) == '1' ? 1: 0;
    		int curLen = i + 1;
    		allDiff = allDiff && a.charAt(i) != b.charAt(i);
    		allEqual = allEqual && a.charAt(i) == b.charAt(i);
    		//if (allDiff == false && allEqual == false) return false;
    		if (curLen % 2  == 0 && cntPref == curLen / 2) {
    			if (allDiff == false && allEqual == false) return false;
    			if (i == n - 1) return true;
    			allDiff = true;
    			allEqual = true;
    		}
    	} 
    	return allEqual;
    	
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	a = inp.next(); b = inp.next();

        	System.out.println(solve()? "YES" : "NO");

        }
        //System.out.println(solve()? "YES" : "NO");
        //out.close();
    }
}