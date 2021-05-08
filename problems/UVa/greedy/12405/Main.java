/*input
3
3
.#.
11
...##....##
2
##
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
    static String field;
    
    static int numCrows(int seg) {
    	return seg / 3 + (seg % 3 == 0? 0 : 1);
    }
    static int solve() {
    	int res = 0, covered = -1;
    	for (int i = 0; i < n; i++) {
    		if (field.charAt(i) == '.' && i > covered) {
    			covered = Math.min(n - 1, i + 2);
    			res++;
    		}
    	}
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
      	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	field = inp.next();
        	System.out.println("Case " + test + ": " + solve());
        }
        
        //out.close();
    }
}