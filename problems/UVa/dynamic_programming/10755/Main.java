/*input
1
2 2 2
-1 2 0 -3 -2 -1 1 5
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

    static int a, b, c;
    static long[][][] sum;

    static long solve() {
    	long res = sum[1][1][1];
    	for (int x1 = 1; x1 <= b; x1++) {
        	for (int y1 = 1; y1 <= c; y1++) {
        		for (int x2 = x1; x2 <= b; x2++) {
        			for (int y2 = y1; y2 <= c; y2++) {
        				long maxRange = 0;
        				for (int i = 1; i <= a; i++) {
        					long subRec = sum[i][x2][y2] - sum[i][x2][y1 - 1] - sum[i][x1 - 1][y2] + sum[i][x1 - 1][y1 - 1];
        					maxRange += subRec;
        					res = Math.max(res, maxRange);
        					if (maxRange < 0L) maxRange = 0L;
        				}
        			}
        		}
        	}
        }
        return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	a = inp.nextInt(); b = inp.nextInt(); c = inp.nextInt();
        	sum = new long[a + 1][b + 1][c + 1];
        	for (int i = 1; i <= a; i++) 
        		for (int j = 1; j <= b; j++) 
        			for (int k = 1; k <= c; k++) 
        				sum[i][j][k] = inp.nextLong() + sum[i][j - 1][k] + sum[i][j][k - 1] - sum[i][j - 1][k - 1];

        	System.out.println(solve());
  	     	if (numTests != 0) System.out.println(); 
  	     }
        
        //out.close();
    }
}